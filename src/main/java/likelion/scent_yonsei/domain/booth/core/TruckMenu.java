package likelion.scent_yonsei.domain.booth.core;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "truck_menu")
@Getter @Setter
@NoArgsConstructor
public class TruckMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = true)
    private int price;

    @Column(length = 100)
    private String photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_truck_id", nullable = false)
    private FoodTruck foodTruck;

    @Builder
    public TruckMenu(String name,
                     int price,
                     String photo,
                     FoodTruck foodTruck) {
        this.name = name;
        this.price = price;
        this.photo = photo;
        this.foodTruck = foodTruck;
    }
}
