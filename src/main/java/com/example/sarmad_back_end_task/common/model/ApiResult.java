package com.example.sarmad_back_end_task.common.model;

import lombok.Data;

@Data
public class ApiResult<T> {
    T data;
    boolean success;
    String error;

    ApiResult(T data) {
        success = true;
        this.data = data;
        error = null;
    }

    ApiResult(String error) {
        this.error = error;
        success = false;
        data = null;
    }
}

