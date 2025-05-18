package likelion.scent_yonsei.domain.notice.api.dto;

public record SingleNoticeResponseDto<T>(
        int status,
        boolean success,
        String message,
        T data
) {}
