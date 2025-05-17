package likelion.scent_yonsei.domain.show.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ShowRes(
    @JsonProperty("showId") Long showId,
    String title,
    String start_at,
    String finish_at,
    String photo,
    String section
) {
}
