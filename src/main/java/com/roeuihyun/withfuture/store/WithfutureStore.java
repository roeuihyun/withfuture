package com.roeuihyun.withfuture.store;

import java.util.List;

import com.roeuihyun.withfuture.dto.UserDTO;

public interface WithfutureStore {

	UserDTO getUser();
	
	List<UserDTO> getAllUser();
	
}
