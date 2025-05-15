package likelion.scent_yonsei.domain.booth.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BoothRepository extends JpaRepository<Booth, Long> {

    @Query("""
      SELECT b
      FROM Booth b
      WHERE (:search  IS NULL
             OR b.name        LIKE %:search%
             OR b.description LIKE %:search%)
        AND (:section IS NULL OR b.section = :section)
        AND b.day = :day
    """)
    List<Booth> findFiltered(
            @Param("search")  String search,
            @Param("section") String section,
            @Param("day")     int day
    );
}
