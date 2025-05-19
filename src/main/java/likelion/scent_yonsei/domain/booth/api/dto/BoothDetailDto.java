package likelion.scent_yonsei.domain.booth.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record BoothDetailDto(
        @JsonProperty("boothId") Long        boothId,
        String      name,
        String      organization,
        String      instagram,
        @JsonProperty("photos")       List<String> photos,
        String      description,
        List<MenuDto> menu
) {}
