package com.example.sarmad_back_end_task.common.model;

public class ApiResultUtils {
    private ApiResultUtils() {
    }

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(data);
    }

    public static <T> ApiResult<T> failure(String error) {
        return new ApiResult<>(error);
    }
}
