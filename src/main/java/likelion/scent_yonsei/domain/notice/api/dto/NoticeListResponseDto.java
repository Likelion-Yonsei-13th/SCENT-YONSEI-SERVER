package likelion.scent_yonsei.domain.notice.api.dto;


public record NoticeListResponseDto<T>(
        int status,
        boolean success,
        String message,
        T data
) {

    // 내부 데이터 구조를 감싸는 static record
    public record NoticeListData<T>(
            String search,
            String category,
            T notices
    ) {}
}
