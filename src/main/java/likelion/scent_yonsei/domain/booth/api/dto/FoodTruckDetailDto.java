package likelion.scent_yonsei.domain.booth.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record FoodTruckDetailDto(
        @JsonProperty("foodTruckId") Long         foodTruckId,
        String       name,
        String       instagram,
        @JsonProperty("photo")         String photo,
        String       description,
        List<MenuDto> menu
) {}
