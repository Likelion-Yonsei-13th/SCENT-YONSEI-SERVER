package likelion.scent_yonsei.domain.notice.core.repository;

import likelion.scent_yonsei.domain.notice.core.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findAllByOrderByCreatedAtDesc();

    List<Notice> findByCategoryOrderByCreatedAtDesc(String category);

    List<Notice> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrderByCreatedAtDesc(String title, String content);

    @Query("SELECT n FROM Notice n WHERE n.category = :category AND " +
            "(LOWER(n.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            " OR LOWER(n.content) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "ORDER BY n.createdAt DESC")
    List<Notice> findByCategoryAndTitleOrContentOrderByCreatedAtDesc(
            @Param("category") String category,
            @Param("keyword") String keyword
    );
}
