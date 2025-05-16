package likelion.scent_yonsei.domain.notice.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import likelion.scent_yonsei.domain.notice.core.notice.Notice;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class NoticeDetailResponseDto {

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

    private List<String> photo;
    

    public static NoticeDetailResponseDto fromEntity(Notice notice, List<String> photoUrls) {
        return NoticeDetailResponseDto.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .importance(notice.isImportance())
                .category(notice.getCategory())
                .createdAt(notice.getCreatedAt().toString())
                .updatedAt(notice.getUpdatedAt().toString())
                .photo(photoUrls)
                .build();
    }
}
