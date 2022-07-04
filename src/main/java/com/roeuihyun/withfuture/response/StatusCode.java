package com.roeuihyun.withfuture.response;

import org.springframework.http.HttpStatus;

public interface StatusCode {

	HttpStatus getHttpStatus();
    String name();
    String getMessage();
	
}
