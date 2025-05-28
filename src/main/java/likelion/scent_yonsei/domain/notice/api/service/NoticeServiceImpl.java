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
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final NoticePhotoRepository noticePhotoRepository;

    /**
     * 공지사항 목록 필터링 조회 (대표 사진 포함)
     */
    @Transactional(readOnly = true)
    @Override
    public List<NoticeResponseDto> getFilteredNotices(String search, String category) {
        boolean hasSearch = search != null && !search.trim().isEmpty();
        boolean hasCategory = category != null && !category.trim().isEmpty();

        List<Notice> notices;

        if (hasSearch && hasCategory) {
            notices = noticeRepository.findByCategoryAndTitleOrContentOrderByCreatedAtDesc(category, search);
        } else if (hasSearch) {
            notices = noticeRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrderByCreatedAtDesc(search, search);
        } else if (hasCategory) {
            notices = noticeRepository.findByCategoryOrderByCreatedAtDesc(category);
        } else {
            notices = noticeRepository.findAllByOrderByCreatedAtDesc();
        }

        return notices.stream()
                .map(notice -> {
                    List<NoticePhoto> photos = noticePhotoRepository.findAllByNoticeId(notice.getId());
                    String photoUrl = photos.isEmpty() ? null : photos.get(0).getPhoto();
                    return new NoticeResponseDto(
                            notice.getId(),
                            notice.getTitle(),
                            notice.getContent(),
                            notice.isImportance(),
                            notice.getCategory(),
                            notice.getCreatedAt(),
                            notice.getUpdatedAt(),
                            photoUrl
                    );
                })
                .collect(Collectors.toList());
    }

    /**
     * 공지사항 상세 정보 조회
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<NoticeDetailResponseDto> getNoticeDetail(Long noticeId) {
        return noticeRepository.findById(noticeId).map(notice -> {
            List<String> photoUrls = noticePhotoRepository.findAllByNoticeId(noticeId).stream()
                    .map(NoticePhoto::getPhoto)
                    .collect(Collectors.toList());

            return new NoticeDetailResponseDto(
                    notice.getId(),
                    notice.getTitle(),
                    notice.getContent(),
                    notice.isImportance(),
                    notice.getCategory(),
                    notice.getCreatedAt(),
                    notice.getUpdatedAt(),
                    photoUrls
            );
        });
    }
}