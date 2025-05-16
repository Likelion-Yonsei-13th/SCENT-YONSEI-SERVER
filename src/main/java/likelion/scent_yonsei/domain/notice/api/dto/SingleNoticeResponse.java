package likelion.scent_yonsei.domain.notice.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SingleNoticeResponse<T> {
    private int status;
    private boolean success;
    private String message;
    private T data;
    
}
