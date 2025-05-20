package likelion.scent_yonsei.domain.booth.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record BoothDetailDto(
        @JsonProperty("boothId") Long        boothId,
        String      name,
        String      organization,
        @JsonProperty("section")     String      section,
        @JsonProperty("location")    String     location,
        String      instagram,
        @JsonProperty("photos")       List<String> photos,
        String      description,
        List<MenuDto> menu
) {}
