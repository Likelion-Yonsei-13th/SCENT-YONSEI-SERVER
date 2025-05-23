package likelion.scent_yonsei.domain.booth.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MenuDto(
        @JsonProperty("menu_id") Long   menuId,
        @JsonProperty("menu_name") String menuName,
        Integer    price,
        @JsonProperty("menu_photo") String menuPhoto
) {}
