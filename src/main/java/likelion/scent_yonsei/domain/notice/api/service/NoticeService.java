package likelion.scent_yonsei.domain.notice.api.service;

import likelion.scent_yonsei.domain.notice.api.dto.NoticeResponseDto;
import likelion.scent_yonsei.domain.notice.api.dto.NoticeDetailResponseDto;

import java.util.List;
import java.util.Optional;

public interface NoticeService {

    // 공지사항 리스트 필터링 조회
    List<NoticeResponseDto> getFilteredNotices(String search, String category);

    // 공지사항 상세 조회
    Optional<NoticeDetailResponseDto> getNoticeDetail(Long noticeId);
}
