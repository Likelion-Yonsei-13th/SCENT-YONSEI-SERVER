package likelion.scent_yonsei.makers.core;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="review")
@Getter
@Setter
@NoArgsConstructor

public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "review")
    private String review;

    public Review(String review) {
        this.review = review;
    }

}
