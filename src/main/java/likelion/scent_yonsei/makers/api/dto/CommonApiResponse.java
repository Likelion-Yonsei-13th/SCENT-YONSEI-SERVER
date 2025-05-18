package likelion.scent_yonsei.makers.api.dto;

public class CommonApiResponse<T> {
    private final int status;
    private final boolean success;
    private final String message;
    private final T data;

    public CommonApiResponse(int status, boolean success, String message, T data) {
        this.status = status;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
