package com.roeuihyun.withfuture.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.roeuihyun.withfuture.response.CommonStatusCode;
import com.roeuihyun.withfuture.response.StatusCode;
import com.roeuihyun.withfuture.response.StatusResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<Object> handleCustomException(RestApiException e) {
        StatusCode statusCode = e.getStatusCode();
        return handleExceptionInternal(statusCode);
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException e) {
        log.warn("handleIllegalArgument", e);
        StatusCode errorCode = CommonStatusCode.INVALID_PARAMETER;
        return handleExceptionInternal(errorCode, e.getMessage());
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        log.warn("handleIllegalArgument", e);
        StatusCode errorCode = CommonStatusCode.INVALID_PARAMETER;
        return handleExceptionInternal(e, errorCode);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAllException(Exception ex) {
        log.warn("handleAllException", ex);
        StatusCode errorCode = CommonStatusCode.INTERNAL_SERVER_ERROR;
        return handleExceptionInternal(errorCode);
    }

    private ResponseEntity<Object> handleExceptionInternal(StatusCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(errorCode));
    }

    private StatusResponse makeErrorResponse(StatusCode statusCode) {
        return StatusResponse.builder()
                .code(statusCode.name())
                .message(statusCode.getMessage())
                .build();
    }

    private ResponseEntity<Object> handleExceptionInternal(StatusCode statusCode, String message) {
        return ResponseEntity.status(statusCode.getHttpStatus())
                .body(makeErrorResponse(statusCode, message));
    }

    private StatusResponse makeErrorResponse(StatusCode statusCode, String message) {
        return StatusResponse.builder()
                .code(statusCode.name())
                .message(message)
                .build();
    }

    private ResponseEntity<Object> handleExceptionInternal(BindException e, StatusCode statusCode) {
        return ResponseEntity.status(statusCode.getHttpStatus())
                .body(makeErrorResponse(e, statusCode));
    }

    private StatusResponse makeErrorResponse(BindException e, StatusCode statusCode) {
        List<StatusResponse.Validation> validationErrorList = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(StatusResponse.Validation::of)
                .collect(Collectors.toList());

        return StatusResponse.builder()
                .code(statusCode.name())
                .message(statusCode.getMessage())
                .errors(validationErrorList)
                .build();
    }
}