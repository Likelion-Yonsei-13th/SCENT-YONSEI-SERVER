package likelion.scent_yonsei.domain.booth.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SearchErrorDto(
        @JsonProperty("search") String search
) {}
