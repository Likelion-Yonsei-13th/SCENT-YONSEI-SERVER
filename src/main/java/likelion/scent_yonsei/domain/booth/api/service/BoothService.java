package likelion.scent_yonsei.domain.booth.api.service;

import likelion.scent_yonsei.domain.booth.api.dto.*;
import likelion.scent_yonsei.domain.booth.core.*;
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
        String q = (search   == null || search.isBlank())   ? null : search;
        String s = (section  == null || section.isBlank())  ? null : section;
        String c = (category == null || category.isBlank()) ? "전체" : category;

        // 1) booth 리스트
        List<BoothSummaryDto> booths = boothRepo.findFiltered(q, s, day, c).stream()
                .map(b -> new BoothSummaryDto(
                        b.getId(),
                        b.getName(),
                        b.getOrganization(),
                        b.getPhotos().isEmpty()
                                ? ""
                                : b.getPhotos().get(0).getPhoto()
                ))
                .toList();

        // 2) foodTruck 리스트
        List<FoodTruckSummaryDto> trucks = truckRepo.findFiltered(q, s, day, c).stream()
                .map(t -> new FoodTruckSummaryDto(
                        t.getId(),
                        t.getName(),
                        t.getMenus().isEmpty()
                                ? ""
                                : t.getMenus().get(0).getPhoto()
                ))
                .toList();

        // 3) 결과 조합하여 DTO 생성
        //    → 이제 search/day/section/category 를 모두 data 안으로 넣어줍니다.
        BoothListDataDto data = new BoothListDataDto(
                /*  순서가 변경되었어요!  */
                q   == null ? "" : q,   // search
                day,                    // day
                s   == null ? "" : s,   // section
                c,                      // category
                booths,                 // booth 리스트
                trucks                  // foodTruck 리스트
        );

        // 4) “검색어가 주어졌는데 아무 결과가 없으면” 에러
        if (q != null && booths.isEmpty() && trucks.isEmpty()) {
            String msg = String.format("search에 해당하는 부스 정보가 없습니다.", q);
            return new BoothListResponseDto(
                    404,
                    false,
                    msg,
                    data
            );
        }

        // 5) 정상 리턴
        return new BoothListResponseDto(
                200,
                true,
                "부스 정보 반환 성공",
                data
        );
    }

    @Transactional(readOnly = true)
    public DetailResponseDto<?> getDetail(Long id, String category) {
        // BOOTH 카테고리
        if ("booth".equalsIgnoreCase(category)) {
            return boothRepo.findById(id)
                    .<DetailResponseDto<?>>map(b -> {
                        List<MenuDto> menu = List.of();
                        if (Boolean.TRUE.equals(b.getIsFoodBooth())) {
                            menu = b.getMenus().stream()
                                    .map(m -> new MenuDto(m.getId(), m.getName(), m.getPrice()))
                                    .collect(Collectors.toList());
                        }
                        // ─── 사진 전체를 URL 리스트로 수집
                        List<String> photoUrls = b.getPhotos().stream()
                                .map(BoothPhoto::getPhoto)        // BoothPhoto#getPhoto
                                .toList();

                        BoothDetailDto dto = new BoothDetailDto(
                                b.getId(),
                                b.getName(),
                                b.getOrganization(),
                                b.getInstagram(),
                                photoUrls,
                                b.getDescription(),
                                menu
                        );
                        return new DetailResponseDto<>(
                                200,
                                true,
                                "부스 상세 정보 반환 성공",
                                "booth",
                                dto
                        );
                    })
                    .orElseGet(() -> new DetailResponseDto<>(
                            404,
                            false,
                            "boothId에 맞는 부스 정보가 존재하지 않습니다.",
                            "booth",
                            new IdErrorDto(id, null)
                    ));
        }
        // FOODTRUCK 카테고리
        else if ("foodTruck".equalsIgnoreCase(category)) {
            return truckRepo.findById(id)
                    .<DetailResponseDto<?>>map(t -> {
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
                                200,
                                true,
                                "부스 상세 정보 반환 성공",
                                "foodTruck",
                                dto
                        );
                    })
                    .orElseGet(() -> new DetailResponseDto<>(
                            404,
                            false,
                            "foodTruckId에 맞는 푸드트럭 정보가 존재하지 않습니다.",
                            "foodTruck",
                            new IdErrorDto(null, id)
                    ));
        }
        // category 파라미터 오류
        return new DetailResponseDto<>(
                400,
                false,
                "Unknown category: " + category,
                category,
                null
        );
    }
}
