package likelion.scent_yonsei.domain.show.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ShowDetailRes(
        @JsonProperty("showId") Long showId,
        String title,
        String name,
        String instagram,
        String section,
        String day,
        String start_at,
        String finish_at,
        String description,
        List<String> photo
) {
}
