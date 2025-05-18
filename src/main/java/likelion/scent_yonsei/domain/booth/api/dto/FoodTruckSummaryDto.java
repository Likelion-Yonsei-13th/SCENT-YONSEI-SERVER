package likelion.scent_yonsei.domain.booth.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FoodTruckSummaryDto(
        @JsonProperty("foodTruckId") Long   foodTruckId,
        String name,
        String photo
) {}
