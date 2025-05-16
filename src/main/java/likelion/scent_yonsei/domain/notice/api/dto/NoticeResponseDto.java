package likelion.scent_yonsei.domain.notice.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import likelion.scent_yonsei.domain.notice.core.notice.Notice;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoticeResponseDto {

    @JsonProperty("noticeId")
    private Long id;

    private String title;
    private String content;
    private boolean importance;
    private String category;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    public static NoticeResponseDto fromEntity(Notice notice) {
        return new NoticeResponseDto(
                notice.getId(),
                notice.getTitle(),
                notice.getContent(),
                notice.isImportance(),
                notice.getCategory(),
                notice.getCreatedAt(),
                notice.getUpdatedAt()
        );
    }
}
