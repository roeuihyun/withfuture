package com.roeuihyun.withfuture.service;

import java.util.List;

import com.roeuihyun.withfuture.dto.UserDTO;

public interface WithfutureService {
	
	UserDTO getUser();
	
	List<UserDTO> getAllUser();

}
