package likelion.scent_yonsei.domain.booth.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BoothSummaryDto(
        @JsonProperty("boothId") Long   boothId,
        String name,
        String organization,
        String photo
) {}
