/*=================================================================================
 *                        Copyright(c) 2022 WithFuture
 *
 * Project                : withfuture
 * Source File Name       : com.roeuihyun.withfuture.advice.GlobalControllerAdvice
 * Description            :
 * Author                 : 노의현
 * Version                : 1.0.0
 * Created Date           : 2022.08.01
 * Updated Date           : 2022.08.01
 * Last Modifier          : 노의현
 * Updated Contents       : 
 * 2022.08.01 최초 작성
 *===============================================================================*/
package com.roeuihyun.withfuture.advice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.roeuihyun.withfuture.enums.CommonStatusCode;
import com.roeuihyun.withfuture.enums.StatusCode;
import com.roeuihyun.withfuture.exception.BizException;
import com.roeuihyun.withfuture.exception.RestApiException;
import com.roeuihyun.withfuture.response.ErrorStatusResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<Object> handleRestApiException(RestApiException e) {
    	log.warn("handleRestApiException : ", e);
        StatusCode statusCode = e.getStatusCode();
        return handleExceptionInternal(statusCode);
    }
    
    @ExceptionHandler(BizException.class)
    public ResponseEntity<Object> handleBizException(BizException e) {
    	log.warn("handleBizException : ", e);
    	StatusCode statusCode = e.getStatusCode();
        return handleExceptionInternal(statusCode);
    }
    
	@ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException e) {
        log.warn("handleIllegalArgument : ", e);
        StatusCode statusCode = CommonStatusCode.BAD_REQUEST;
        return handleExceptionInternal(statusCode, e.getMessage());
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        log.warn("handleMethodArgumentNotValid : ", e);
        StatusCode statusCode = CommonStatusCode.BAD_REQUEST;
        return handleExceptionInternal(e.getBindingResult(), statusCode);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception ex) {
        log.warn("handleAllException : ", ex);
        StatusCode statusCode = CommonStatusCode.INTERNAL_SERVER_ERROR;
        return handleExceptionInternal(statusCode);
    }
    
    private ResponseEntity<Object> handleExceptionInternal(StatusCode statusCode, String message) {
        return ResponseEntity.status(statusCode.getHttpStatus())
                .body(buildErrorResponse(statusCode, message));
    }
    
	private ResponseEntity<Object> handleExceptionInternal(BindingResult bindingResult, StatusCode statusCode) {
        return ResponseEntity.status(statusCode.getHttpStatus())
                .body(buildErrorResponse(bindingResult, statusCode));
    }

	private ResponseEntity<Object> handleExceptionInternal(StatusCode statusCode) {
        return ResponseEntity.status(statusCode.getHttpStatus())
                .body(buildErrorResponse(statusCode));
    }

    private ErrorStatusResponse buildErrorResponse(StatusCode statusCode) {
        return ErrorStatusResponse.builder()
//                .http_code(statusCode.name())
                .biz_code(statusCode.getBiz_code())
                .message(statusCode.getMessage())
                .build();
    }

    private ErrorStatusResponse buildErrorResponse(StatusCode statusCode, String message) {
        return ErrorStatusResponse.builder()
//                .http_code(statusCode.name())
                .biz_code(statusCode.getBiz_code())
                .message(message)
                .build();
    }

    private ErrorStatusResponse buildErrorResponse(BindingResult bindingResult, StatusCode statusCode) {
        List<ErrorStatusResponse.Validation> validationErrorList = bindingResult.getFieldErrors()
                .stream()
                .map(ErrorStatusResponse.Validation::of)
                .collect(Collectors.toList());

        return ErrorStatusResponse.builder()
//                .http_code(statusCode.name())
                .biz_code(statusCode.getBiz_code())
                .message(statusCode.getMessage())
                .errors(validationErrorList)
                .build();
    }
    
}