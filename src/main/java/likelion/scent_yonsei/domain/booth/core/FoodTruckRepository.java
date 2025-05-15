package likelion.scent_yonsei.domain.booth.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface FoodTruckRepository extends JpaRepository<FoodTruck, Long> {

    @Query("""
      SELECT t
      FROM FoodTruck t
      WHERE (:search  IS NULL
             OR t.name        LIKE %:search%
             OR t.description LIKE %:search%)
        AND (:section IS NULL OR t.section = :section)
        AND t.day = :day
    """)
    List<FoodTruck> findFiltered(
            @Param("search")  String search,
            @Param("section") String section,
            @Param("day")     int day
    );
}
