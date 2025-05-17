package likelion.scent_yonsei.domain.booth.core;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "booth_menu")
@Getter @Setter
@NoArgsConstructor
public class BoothMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(length = 100)
    private String photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booth_id", nullable = false)
    private Booth booth;

    @Builder
    public BoothMenu(String name,
                     int price,
                     String photo,
                     Booth booth) {
        this.name = name;
        this.price = price;
        this.photo = photo;
        this.booth = booth;
    }
}
