package likelion.scent_yonsei.domain.notice.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import likelion.scent_yonsei.domain.notice.core.notice.Notice;

import java.util.List;

public record NoticeDetailResponseDto(
        @JsonProperty("noticeId") Long id,
        String title,
        String content,
        boolean importance,
        String category,
        @JsonProperty("created_at") String createdAt,
        @JsonProperty("updated_at") String updatedAt,
        List<String> photo
) {
    public static NoticeDetailResponseDto fromEntity(Notice notice, List<String> photoUrls) {
        return new NoticeDetailResponseDto(
                notice.getId(),
                notice.getTitle(),
                notice.getContent(),
                notice.isImportance(),
                notice.getCategory(),
                notice.getCreatedAt().toString(),
                notice.getUpdatedAt().toString(),
                photoUrls
        );
    }
}
