package com.roeuihyun.withfuture.service;

import java.util.List;

import com.roeuihyun.withfuture.dto.UserDTO;
import com.roeuihyun.withfuture.exception.RestApiException;

public interface WithfutureService {
	
	UserDTO getUser();
	
	List<UserDTO> getAllUser();
	
	List<UserDTO> getTestUser() throws RestApiException;

}
