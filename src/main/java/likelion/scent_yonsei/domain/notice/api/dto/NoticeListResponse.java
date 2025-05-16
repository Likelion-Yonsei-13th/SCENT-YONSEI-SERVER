package likelion.scent_yonsei.domain.notice.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoticeListResponse<T> {
    private int status;
    private boolean success;
    private String message;
    private String search;
    private String category;
    private T data;
    
}
