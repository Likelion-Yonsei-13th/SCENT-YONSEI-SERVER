package likelion.scent_yonsei.domain.show.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {

    @Query("""
        SELECT s
          FROM Show s
         WHERE s.startAt <= :now
           AND s.finishAt >= :now
        """)
    List<Show> findLiveShows(@Param("now") LocalDateTime now);

    List<Show> findByDay(Integer day);

}
