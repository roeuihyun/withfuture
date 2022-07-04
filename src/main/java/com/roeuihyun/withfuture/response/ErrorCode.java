package com.roeuihyun.withfuture.response;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

	HttpStatus getHttpStatus();
    String name();
    String getMessage();
	
}
