package likelion.scent_yonsei.domain.notice.core.notice;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notice_photo")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticePhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "photo")
    private String photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id")
    private Notice notice;
}
