package likelion.scent_yonsei.domain.booth.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record IdErrorDto(
        @JsonProperty("boothId") Long boothId,
        @JsonProperty("foodTruckId") Long foodTruckId
) {}
