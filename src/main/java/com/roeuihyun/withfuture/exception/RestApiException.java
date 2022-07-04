package com.roeuihyun.withfuture.exception;

import com.roeuihyun.withfuture.response.StatusCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException {

    private final StatusCode errorCode;

}