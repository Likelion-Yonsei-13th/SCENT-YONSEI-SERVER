package likelion.scent_yonsei.makers.core;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Liked {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
    public Liked() {}

    public Long GetId(){
        return id;
    }

    public LocalDateTime GetCreatedAt(){
        return createdAt;
    }
}