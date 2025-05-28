package likelion.scent_yonsei.domain.show.api.service;

import likelion.scent_yonsei.domain.show.api.dto.LiveRes;
import likelion.scent_yonsei.domain.show.api.dto.ShowRes;
import likelion.scent_yonsei.domain.show.api.dto.ShowDataRes;
import likelion.scent_yonsei.domain.show.core.Show;
import likelion.scent_yonsei.domain.show.core.ShowPhoto;
import org.springframework.transaction.annotation.Transactional;
import likelion.scent_yonsei.common.Response;
import likelion.scent_yonsei.domain.show.api.dto.ShowDetailRes;
import likelion.scent_yonsei.domain.show.core.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShowService {

    private final ShowRepository showRepository;
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm");

    @Transactional(readOnly = true)
    public Response<?> showAll(Integer day) {

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));

        // live 공연
        List<LiveRes> live = showRepository.findLiveShowRes(now).stream()
                .map(s -> {

                    String photo = s.getPhoto().stream()
                            .min(Comparator.comparingLong(ShowPhoto::getId))
                            .map(ShowPhoto::getPhoto)
                            .orElse("");

                    return new LiveRes(
                            s.getId(),
                            s.getTitle(),
                            photo
                    );
                })
                .collect(Collectors.toList());

        // 이외 공연
        List<ShowRes> show = showRepository.findByDay(day).stream()
                .map(s -> {
                    String photo = s.getPhoto().stream()
                            .min(Comparator.comparingLong(ShowPhoto::getId))
                            .map(ShowPhoto::getPhoto)
                            .orElse("");

                    return new ShowRes(
                            s.getId(),
                            s.getTitle(),
                            s.getSection(),
                            s.getStartAt().format(TIME_FMT),
                            s.getFinishAt().format(TIME_FMT),
                            photo
                    );
                })
                .collect(Collectors.toList());

        // data 구성
        ShowDataRes data = new ShowDataRes(live, show);

        return new Response<>(200,true,"공연 정보 반환 성공", data);
    }

    @Transactional(readOnly = true)
    public Response<?> showDetail(Long showId) {

        return showRepository.findById(showId)
                .map(show-> {

                    var photos = show.getPhoto().stream()
                            .map(ShowPhoto::getPhoto)
                            .collect(Collectors.toList());

                    String formattedDay = formatDay(show.getDay());

                    ShowDetailRes showDetail = new ShowDetailRes(
                            show.getId(),
                            show.getTitle(),
                            show.getName(),
                            show.getInstagram(),
                            show.getSection(),
                            formattedDay,
                            show.getStartAt().format(TIME_FMT),
                            show.getFinishAt().format(TIME_FMT),
                            show.getDescription(),
                            photos
                    );
                    return new Response<Object>(200, true, "공연 세부 정보 반환 성공", showDetail);
                })
                .orElseGet(() -> {

                    Map<String, Long> data = Map.of("showId", showId);

                    return new Response<Object>(404, false, "showId에 맞는 공연 정보가 존재하지 않습니다", data);
                });
    }

    private String formatDay(Integer day) {

        return switch (day) {
            case 1 -> "DAY1 27일";
            case 2 -> "DAY2 28일";
            case 3 -> "DAY3 29일";
            case 4 -> "DAY4 30일";
            default -> "DAY" + day + "일";
        };
    }
}
