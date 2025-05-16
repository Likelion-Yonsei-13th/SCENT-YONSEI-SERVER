package likelion.scent_yonsei.domain.notice.core.repository;

import likelion.scent_yonsei.domain.notice.core.notice.NoticePhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticePhotoRepository extends JpaRepository<NoticePhoto, Long> {
    List<NoticePhoto> findAllByNoticeId(Long noticeId);
}
