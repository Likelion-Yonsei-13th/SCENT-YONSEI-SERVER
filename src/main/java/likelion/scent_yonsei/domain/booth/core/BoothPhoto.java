package likelion.scent_yonsei.domain.booth.core;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "booth_photo")
@Getter @Setter
@NoArgsConstructor
@RequiredArgsConstructor      // final 이거나 @NonNull 인 필드만 인자로 받는 생성자 생성
public class BoothPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull                    // RequiredArgsConstructor 에 포함
    @Column(length = 100)
    private String photo;

    @NonNull                    // RequiredArgsConstructor 에 포함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booth_id", nullable = false)
    private Booth booth;
}
