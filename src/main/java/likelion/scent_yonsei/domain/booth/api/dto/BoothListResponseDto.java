package likelion.scent_yonsei.domain.booth.api.dto;

public record BoothListResponseDto(
        int                status,
        boolean            success,
        String             message,
        String             search,
        int                day,
        String             section,
        String             category,
        BoothListDataDto   data
) {}
