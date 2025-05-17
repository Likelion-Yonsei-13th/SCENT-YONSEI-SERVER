package likelion.scent_yonsei.domain.notice.core.repository;

import likelion.scent_yonsei.domain.notice.core.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findByCategory(String category);

    List<Notice> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title, String content);

    @Query("SELECT n FROM Notice n WHERE n.category = :category AND " +
            "(LOWER(n.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(n.content) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<Notice> findByCategoryAndTitleOrContent(@Param("category") String category,
                                                 @Param("keyword") String keyword);
}
