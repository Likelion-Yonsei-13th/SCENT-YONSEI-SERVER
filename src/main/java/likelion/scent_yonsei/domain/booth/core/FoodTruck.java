package likelion.scent_yonsei.domain.booth.core;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "food_truck")
@Getter @Setter
@NoArgsConstructor
public class FoodTruck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, length = 50)
    private String name;

    @Column(nullable = true)
    private int day;

    @Column(length = 50)
    private String section;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String instagram;

    @Column(length = 150)
    private String photo;

    @Column(name = "food_type", nullable = false, length = 10)
    private String foodType;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "foodTruck", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TruckMenu> menus = new ArrayList<>();

    @Builder
    public FoodTruck(String name,
                     int day,
                     String section,
                     String description,
                     String foodType,
                     String instagram) {
        this.name = name;
        this.day = day;
        this.section = section;
        this.description = description;
        this.instagram = instagram;
        this.foodType = foodType;
    }
}
