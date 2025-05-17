package likelion.scent_yonsei.domain.show.core;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "shows")
@Getter
@Setter
@RequiredArgsConstructor
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 50)
    private String title;

    private Integer day;

    @Column(name = "start_at")
    private LocalDateTime startAt;

    @Column(name = "finish_at")
    private LocalDateTime finishAt;

    @Column(length = 10)
    private String section;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String instagram;

    @Column(length = 100)
    private String photo;

    private Long liked;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_student")
    private Boolean student;

}
