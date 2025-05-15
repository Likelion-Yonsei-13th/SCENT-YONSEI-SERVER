package likelion.scent_yonsei.domain.booth.api.service;

import likelion.scent_yonsei.domain.booth.api.dto.*;
import likelion.scent_yonsei.domain.booth.core.Booth;
import likelion.scent_yonsei.domain.booth.core.BoothRepository;
import likelion.scent_yonsei.domain.booth.core.FoodTruck;
import likelion.scent_yonsei.domain.booth.core.FoodTruckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoothService {

    private final BoothRepository      boothRepo;
    private final FoodTruckRepository  truckRepo;

    @Transactional(readOnly = true)
    public BoothListResponseDto getBooths(
            String search,
            String section,
            String category,
            int    day
    ) {
        // 빈문자열→null, category 빈→"전체"
        String q = (search  == null || search.isBlank())  ? null : search;
        String s = (section == null || section.isBlank()) ? null : section;
        String c = (category== null || category.isBlank())? "전체" : category;

        // 1) booth 리스트
        List<BoothSummaryDto> booths = Collections.emptyList();
        if (c.equals("전체") || c.equals("부스")) {
            booths = boothRepo.findFiltered(q, s, day).stream()
                    .map(b -> new BoothSummaryDto(
                            b.getId(),
                            b.getName(),
                            b.getOrganization(),
                            b.getPhotos().isEmpty()
                                    ? ""
                                    : b.getPhotos().get(0).getPhoto()
                    ))
                    .toList();
        }

        // 2) foodTruck 리스트
        List<FoodTruckSummaryDto> trucks = Collections.emptyList();
        if (c.equals("전체") || c.equals("푸드트럭")) {
            trucks = truckRepo.findFiltered(q, s, day).stream()
                    .map(t -> new FoodTruckSummaryDto(
                            t.getId(),
                            t.getName(),
                            t.getMenus().isEmpty()
                                    ? ""
                                    : t.getMenus().get(0).getPhoto()
                    ))
                    .toList();
        }

        // 합쳐서 반환
        BoothListDataDto data = new BoothListDataDto(booths, trucks);
        return new BoothListResponseDto(
                200,
                true,
                "부스 정보 반환 성공",
                search == null ? "" : search,
                day,
                section == null ? "" : section,
                c,
                data
        );
    }

    @Transactional(readOnly = true)
    public DetailResponseDto<?> getDetail(Long id, String category) {
        if ("booth".equalsIgnoreCase(category)) {
            Booth b = boothRepo.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("부스를 찾을 수 없습니다: " + id));

            // isFoodBooth == true 일 때만 메뉴 반환
            List<MenuDto> menu = List.of();
            if (b.isFoodBooth()) {
                menu = b.getMenus().stream()
                        .map(m -> new MenuDto(m.getId(), m.getName(), m.getPrice()))
                        .collect(Collectors.toList());
            }

            // 대표 사진: photos 첫번째 또는 빈 문자열
            String photo = b.getPhotos().isEmpty()
                    ? ""
                    : b.getPhotos().get(0).getPhoto();

            BoothDetailDto dto = new BoothDetailDto(
                    b.getId(),
                    b.getName(),
                    b.getOrganization(),
                    b.getInstagram(),
                    photo,
                    b.getDescription(),
                    menu
            );

            return new DetailResponseDto<>(
                    200, true, "부스 상세 정보 반환 성공", "booth", dto
            );
        }
        else if ("foodTruck".equalsIgnoreCase(category)) {
            FoodTruck t = truckRepo.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("푸드트럭을 찾을 수 없습니다: " + id));

            List<MenuDto> menu = t.getMenus().stream()
                    .map(m -> new MenuDto(m.getId(), m.getName(), m.getPrice()))
                    .collect(Collectors.toList());

            FoodTruckDetailDto dto = new FoodTruckDetailDto(
                    t.getId(),
                    t.getName(),
                    t.getInstagram(),
                    t.getDescription(),
                    menu
            );

            return new DetailResponseDto<>(
                    200, true, "부스 상세 정보 반환 성공", "foodTruck", dto
            );
        }
        else {
            throw new IllegalArgumentException("Unknown category: " + category);
        }
    }
}
