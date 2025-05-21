package likelion.scent_yonsei.domain.booth.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface FoodTruckRepository extends JpaRepository<FoodTruck, Long> {

    @Query("""
      SELECT t
      FROM FoodTruck t
      WHERE (:search      IS NULL
             OR t.name        LIKE %:search%
             OR t.description LIKE %:search%)
        AND t.day = :day
        AND (:category = '전체' OR :category = '푸드트럭')
        AND (:foodType = '전체' OR t.foodType = :foodType)
    """)
    List<FoodTruck> findFiltered(
            @Param("search")   String search,
            @Param("day")      int day,
            @Param("category") String category,
            @Param("foodType") String foodType
    );
}
