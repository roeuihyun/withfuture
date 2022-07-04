package com.roeuihyun.withfuture.store;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.roeuihyun.withfuture.dto.UserDTO;

@Component
public class WithfutureStoreImpl implements WithfutureStore{

	@Override
	public UserDTO getUser() {
		return new UserDTO("111","name");
	}

	@Override
	public List<UserDTO> getAllUser() {
		List<UserDTO> list = new ArrayList<UserDTO>();
		list.add(new UserDTO("111","name111"));
		list.add(new UserDTO("222","name222"));
		return list;
	}

}
