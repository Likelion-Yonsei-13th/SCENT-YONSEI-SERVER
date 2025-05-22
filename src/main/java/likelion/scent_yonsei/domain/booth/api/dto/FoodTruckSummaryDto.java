package likelion.scent_yonsei.domain.booth.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record FoodTruckSummaryDto(
        @JsonProperty("foodTruckId") Long   foodTruckId,
        String name,
        String photo,
        @JsonProperty("foodType")        String    foodType,
        @JsonProperty("menuNames")   List<String> menuNames
) {}
