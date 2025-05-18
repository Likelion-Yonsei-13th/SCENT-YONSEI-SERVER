package likelion.scent_yonsei.domain.booth.core;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "booth")
@Getter @Setter
@NoArgsConstructor
public class Booth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50)
    private String organization;

    @Column(nullable = false)
    private int day;

    @Column(length = 10)
    private String section;

    private Integer location;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String instagram;

    private Long liked;

    @Column(name = "location_photo", length = 150)
    private String locationPhoto;

    @Column(name = "is_food_booth", nullable = false)
    private Boolean isFoodBooth;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // 1 : N 메뉴
    @OneToMany(mappedBy = "booth", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoothMenu> menus = new ArrayList<>();

    // 1 : N 사진
    @OneToMany(mappedBy = "booth", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoothPhoto> photos = new ArrayList<>();

    @Builder
    public Booth(String name,
                 String organization,
                 int day,
                 String section,
                 Integer location,
                 String description,
                 String instagram,
                 Long liked,
                 String locationPhoto,
                 Boolean isFoodBooth) {
        this.name = name;
        this.organization = organization;
        this.day = day;
        this.section = section;
        this.location = location;
        this.description = description;
        this.instagram = instagram;
        this.liked = liked;
        this.locationPhoto = locationPhoto;
        this.isFoodBooth = isFoodBooth;
    }
}

