package com.example.sarmad_back_end_task.order.exception_handler;

import com.example.sarmad_back_end_task.common.model.ApiResult;
import com.example.sarmad_back_end_task.common.model.ApiResultUtils;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.MissingRequestValueException;

@RestControllerAdvice
@Hidden
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResult<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldError().getField();
        log.debug(errorMessage);
        return ResponseEntity.badRequest().body(ApiResultUtils.failure(errorMessage));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResult<Void>> handleConstraintViolationException(ConstraintViolationException ex) {
        StringBuilder stringBuilder = new StringBuilder();
        ex.getConstraintViolations()
                .stream()
                .map(constraintViolation -> constraintViolation.getPropertyPath().toString() + " " + constraintViolation.getMessage())
                .forEach(stringBuilder::append);
        log.debug(stringBuilder.toString());
        return ResponseEntity.badRequest().body(ApiResultUtils.failure(stringBuilder.toString()));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ApiResult<Void>> handleWebExchangeBindException(WebExchangeBindException ex) {
        var field = ex.getBindingResult().getFieldError();
        var errorMessage = field.getField() + " " + field.getDefaultMessage();
        log.debug(errorMessage);
        return ResponseEntity.badRequest().body(ApiResultUtils.failure(errorMessage));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResult<Void>> handleGenericException(Exception ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResultUtils.failure("Internal server error: " + ex.getMessage()));
    }

    @ExceptionHandler(MissingRequestValueException.class)
    public ResponseEntity<ApiResult<Void>> handleMissingRequestValueException(MissingRequestValueException ex) {
        log.debug(ex.getReason());
        return ResponseEntity.badRequest().body(ApiResultUtils.failure(ex.getReason()));
    }
}
