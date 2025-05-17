package likelion.scent_yonsei.domain.show.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ShowRes(
        @JsonProperty("showId") Long showId,
        String title,
        String section,
        @JsonProperty("start_at")  String startAt,
        @JsonProperty("finish_at") String finishAt,
        String photo
) {

}
