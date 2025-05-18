package likelion.scent_yonsei.domain.booth.api.dto;

public record BoothListResponseDto(
        int               status,
        boolean           success,
        String            message,
        BoothListDataDto  data
) {}
