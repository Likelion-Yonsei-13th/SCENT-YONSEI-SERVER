package likelion.scent_yonsei.domain.show.api.service;

import likelion.scent_yonsei.domain.show.api.dto.LiveRes;
import likelion.scent_yonsei.domain.show.api.dto.ShowRes;
import likelion.scent_yonsei.domain.show.api.dto.ShowDataRes;
import org.springframework.transaction.annotation.Transactional;
import likelion.scent_yonsei.common.Response;
import likelion.scent_yonsei.domain.show.api.dto.ShowDetailRes;
import likelion.scent_yonsei.domain.show.core.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShowService {

    private final ShowRepository showRepository;
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm");

    @Transactional(readOnly = true)
    public Response<?> showAll(Integer day){

        LocalDateTime now = LocalDateTime.now();

        // live 공연
        List<LiveRes> live = showRepository
                .findLiveShows(now)
                .stream()
                .map(s -> new LiveRes(s.getId(), s.getTitle(), s.getPhoto()))
                .collect(Collectors.toList());

        // 이외 공연
        List<ShowRes> show = showRepository.findByDay(day).stream()
                .map(s -> new ShowRes(
                        s.getId(),
                        s.getTitle(),
                        s.getStartAt().format(TIME_FMT),
                        s.getFinishAt().format(TIME_FMT),
                        s.getPhoto(),
                        s.getSection()
                ))
                .collect(Collectors.toList());

        // data 구성
        ShowDataRes data = new ShowDataRes(live, show);

        return new Response<>(200,true,"공연 정보 반환 성공", data);
    }

    @Transactional(readOnly = true)
    public Response<?> showDetail(Long showId) {

        return showRepository.findById(showId)
                .map(show-> {
                    ShowDetailRes showDetail = new ShowDetailRes(show.getId(),
                            show.getTitle(),
                            show.getName(),
                            show.getInstagram(),
                            show.getSection(),
                            show.getStartAt().format(TIME_FMT),
                            show.getFinishAt().format(TIME_FMT),
                            show.getDescription(),
                            show.getPhoto()
                    );
                    return new Response<Object>(200, true, "공연 세부 정보 반환 성공", showDetail);
                })
                .orElseGet(() -> {

                    Map<String, Long> data = Map.of("showId", showId);

                    return new Response<Object>(404, false, "showId에 맞는 공연 정보가 존재하지 않습니다", data);
                });
    }
}
