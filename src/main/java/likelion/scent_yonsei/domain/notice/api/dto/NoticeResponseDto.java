package likelion.scent_yonsei.domain.notice.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import likelion.scent_yonsei.domain.notice.core.notice.Notice;

public record NoticeResponseDto(
        @JsonProperty("noticeId") Long id,
        String title,
        String content,
        boolean importance,
        String category,
        @JsonProperty("created_at") String createdAt,
        @JsonProperty("updated_at") String updatedAt,
        String photoUrl
) {
    public static NoticeResponseDto fromEntity(Notice notice, String photoUrl) {
        return new NoticeResponseDto(
                notice.getId(),
                notice.getTitle(),
                notice.getContent(), // 기존 content 필드 유지
                notice.isImportance(),
                notice.getCategory(),
                notice.getCreatedAt(),
                notice.getUpdatedAt(),
                photoUrl
        );
    }
}
