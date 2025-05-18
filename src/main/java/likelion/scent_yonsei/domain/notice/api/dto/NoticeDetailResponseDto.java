package likelion.scent_yonsei.domain.notice.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;

public record NoticeDetailResponseDto(
        @JsonProperty("noticeId") Long id,
        String title,
        String content,
        boolean importance,
        String category,
        @JsonProperty("created_at") LocalDateTime createdAt,
        @JsonProperty("updated_at") LocalDateTime updatedAt,
        List<String> photo
) {}
