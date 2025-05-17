package likelion.scent_yonsei.domain.booth.core;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "booth_photo")
@Getter @Setter
@NoArgsConstructor
public class BoothPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booth_id", nullable = false)
    private Booth booth;

    @Builder
    public BoothPhoto(String photo,
                      Booth booth) {
        this.photo = photo;
        this.booth = booth;
    }
}
