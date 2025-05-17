package likelion.scent_yonsei.domain.show.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ShowDetailRes(
        @JsonProperty("showId") Long showId,
        String title,
        String name,
        String instagram,
        String section,
        String start_at,
        String finish_at,
        String description,
        String photo
) {
}
