package com.roeuihyun.withfuture.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.roeuihyun.withfuture.dto.UserDTO;
import com.roeuihyun.withfuture.exception.RestApiException;
import com.roeuihyun.withfuture.response.CommonErrorCode;
import com.roeuihyun.withfuture.store.WithfutureStore;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WithfutureServiceImpl implements WithfutureService{
	
	private final WithfutureStore withfutureStore;

	@Override
	public UserDTO getUser() {
		return withfutureStore.getUser();
	}

	@Override
	public List<UserDTO> getAllUser() {
		return withfutureStore.getAllUser();
	}
	
	@Override
	public List<UserDTO> getTestUser() throws RestApiException {
		throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
//		return withfutureStore.getAllUser();
	}

}
