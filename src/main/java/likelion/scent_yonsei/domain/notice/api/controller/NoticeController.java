package likelion.scent_yonsei.domain.notice.api.controller;

import likelion.scent_yonsei.domain.notice.api.dto.NoticeListResponseDto;
import likelion.scent_yonsei.domain.notice.api.dto.NoticeResponseDto;
import likelion.scent_yonsei.domain.notice.api.dto.NoticeDetailResponseDto;
import likelion.scent_yonsei.domain.notice.api.service.NoticeService;
import likelion.scent_yonsei.domain.notice.api.dto.SingleNoticeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    /**
     * 공지사항 목록 조회
     */

    @GetMapping
    public ResponseEntity<NoticeListResponseDto<NoticeListResponseDto.NoticeListData<List<NoticeResponseDto>>>> getNotices(
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "") String category) {

        try {
            List<NoticeResponseDto> data = noticeService.getFilteredNotices(search, category);

            if (data == null || data.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new NoticeListResponseDto<>(
                                404, false, "검색 결과가 없습니다.",
                                new NoticeListResponseDto.NoticeListData<>(search, category, null)
                        ));
            }

            return ResponseEntity.ok(
                    new NoticeListResponseDto<>(
                            200, true, "공지 목록 조회 성공",
                            new NoticeListResponseDto.NoticeListData<>(search, category, data)
                    )
            );

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    new NoticeListResponseDto<>(
                            400, false, "잘못된 요청: " + e.getMessage(),
                            new NoticeListResponseDto.NoticeListData<>(search, category, null)
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new NoticeListResponseDto<>(
                            500, false, "서버 오류: " + e.getMessage(),
                            new NoticeListResponseDto.NoticeListData<>(search, category, null)
                    )
            );
        }
    }


    @GetMapping("/{noticeId}")
    public ResponseEntity<SingleNoticeResponseDto<NoticeDetailResponseDto>> getNoticeDetail(@PathVariable Long noticeId) {
        try {
            Optional<NoticeDetailResponseDto> detailOpt = noticeService.getNoticeDetail(noticeId);

            if (detailOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new SingleNoticeResponseDto<>(404, false, "공지사항을 찾을 수 없습니다.", null));
            }

            return ResponseEntity.ok(
                    new SingleNoticeResponseDto<>(200, true, "공지사항 상세 정보 반환 성공", detailOpt.get())
            );

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new SingleNoticeResponseDto<>(500, false, "서버 오류: " + e.getMessage(), null));
        }
    }

}
