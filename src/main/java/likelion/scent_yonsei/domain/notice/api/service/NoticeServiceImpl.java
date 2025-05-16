package likelion.scent_yonsei.domain.notice.api.service;

import likelion.scent_yonsei.domain.notice.api.dto.NoticeResponseDto;
import likelion.scent_yonsei.domain.notice.api.dto.NoticeDetailResponseDto;
import likelion.scent_yonsei.domain.notice.core.notice.Notice;
import likelion.scent_yonsei.domain.notice.core.notice.NoticePhoto;
import likelion.scent_yonsei.domain.notice.core.repository.NoticeRepository;
import likelion.scent_yonsei.domain.notice.core.repository.NoticePhotoRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final NoticePhotoRepository noticePhotoRepository;

    /**
     * 공지사항 목록 필터링 조회
     */
    @Override
    public List<NoticeResponseDto> getFilteredNotices(String search, String category) {
        boolean hasSearch = search != null && !search.trim().isEmpty();
        boolean hasCategory = category != null && !category.trim().isEmpty();

        List<Notice> notices;

        if (hasSearch && hasCategory) {
            notices = noticeRepository.findByCategoryAndTitleOrContent(category, search);
        } else if (hasSearch) {
            notices = noticeRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(search, search);
        } else if (hasCategory) {
            notices = noticeRepository.findByCategory(category);
        } else {
            notices = noticeRepository.findAll();
        }

        return notices.stream()
                .map(NoticeResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * 공지사항 상세 정보 조회
     */
    @Override
    public Optional<NoticeDetailResponseDto> getNoticeDetail(Long noticeId) {
        return noticeRepository.findById(noticeId).map(notice -> {
            List<String> photoUrls = noticePhotoRepository.findAllByNoticeId(noticeId).stream()
                    .map(NoticePhoto::getPhoto)
                    .collect(Collectors.toList());

            return NoticeDetailResponseDto.fromEntity(notice, photoUrls);
        });
    }
}
