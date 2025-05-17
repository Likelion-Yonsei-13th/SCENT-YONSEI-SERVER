package likelion.scent_yonsei.domain.show.core;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "show_photo")
@Getter
@Setter
@RequiredArgsConstructor
public class ShowPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    @Column(name = "photo_url", length = 100, nullable = false)
    private String photoUrl;
}
