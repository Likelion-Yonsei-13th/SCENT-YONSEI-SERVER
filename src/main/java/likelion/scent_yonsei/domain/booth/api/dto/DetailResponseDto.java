package likelion.scent_yonsei.domain.booth.api.dto;

public record DetailResponseDto<T>(
        int    status,
        boolean success,
        String message,
        String category,
        T      data
) {}
