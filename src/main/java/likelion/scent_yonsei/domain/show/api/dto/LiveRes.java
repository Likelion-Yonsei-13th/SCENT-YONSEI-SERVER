package likelion.scent_yonsei.domain.show.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LiveRes(
        @JsonProperty("showId") Long showId,
        String title,
        String photo
) {
}
