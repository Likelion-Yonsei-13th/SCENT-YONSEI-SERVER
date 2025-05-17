package likelion.scent_yonsei.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Response<T>(
        @JsonProperty("status") int status,
        @JsonProperty("success") boolean success,
        @JsonProperty("message") String message,
        @JsonProperty("data") T data
) {
}
