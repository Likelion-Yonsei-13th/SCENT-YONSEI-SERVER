package likelion.scent_yonsei.domain.notice.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public record NoticeResponseDto(
        @JsonProperty("noticeId") Long id,
        String title,
        String content,
        boolean importance,
        String category,
        @JsonProperty("created_at") LocalDateTime createdAt,
        @JsonProperty("updated_at") LocalDateTime updatedAt,
        String photoUrl
) {}
