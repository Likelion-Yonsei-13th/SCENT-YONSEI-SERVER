package likelion.scent_yonsei.domain.foodBooth.core;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="food_booth")
@Getter
@Setter
@NoArgsConstructor
public class FoodBooth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50)
    private String organization;

    @Column(nullable = false, length = 20)
    private String manager;

    @Column(name = "contact_number", length = 30)
    private String contactNumber;

    @Column(nullable = false)
    private int date;

    @Column(length = 50)
    private String section;

    private Integer location;

    @Column(length = 150)
    private String description;

    public FoodBooth(String name, String organization, String manager, String contactNumber,
                     int date, String section, Integer location, String description) {
        this.name = name;
        this.organization = organization;
        this.manager = manager;
        this.contactNumber = contactNumber;
        this.date = date;
        this.section = section;
        this.location = location;
        this.description = description;
    }

}
