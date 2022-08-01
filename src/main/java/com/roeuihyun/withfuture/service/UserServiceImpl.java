/*=================================================================================
 *                        Copyright(c) 2022 WithFuture
 *
 * Project                : withfuture
 * Source File Name       : com.roeuihyun.withfuture.service.UserServiceImpl
 * Description            :
 * Author                 : 노의현
 * Version                : 1.0.0
 * Created Date           : 2022.08.01
 * Updated Date           : 2022.08.01
 * Last Modifier          : 노의현
 * Updated Contents       : 
 * 2022.08.01 최초 작성
 *===============================================================================*/
package com.roeuihyun.withfuture.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.roeuihyun.withfuture.dto.UserDTO;
import com.roeuihyun.withfuture.enums.BizStatusCode;
import com.roeuihyun.withfuture.exception.BizException;
import com.roeuihyun.withfuture.store.UserStore;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserStore userStore;
	
	@Override
	public Optional<UserDTO> insertUser(HashMap<String, Object> param) {
		Optional<UserDTO> currentUser = userStore.findById((Long)param.get("user_id"));
		if( currentUser.isPresent() ) {
			throw new BizException(BizStatusCode.USER_EXIST);
		}
		userStore.save((UserDTO)param.get("userDTO"));
		return userStore.findById((Long)param.get("user_id"));
	}

	@Override
	public List<UserDTO> getAllUser() {
		return (List<UserDTO>) userStore.findAll();
	}

	@Override
	public Optional<UserDTO> getUserById(HashMap<String, Object> param) {
		return userStore.findById((Long)param.get("user_id"));
	}

	@Override
	public Optional<UserDTO> putUser(HashMap<String, Object> param) {
		Optional<UserDTO> currentUser = userStore.findById((Long)param.get("user_id"));
		if( !currentUser.isPresent() ) {
			throw new BizException(BizStatusCode.USER_NOT_FOUND);		
		}
		userStore.save((UserDTO)param.get("userDTO"));
		return userStore.findById((Long)param.get("user_id"));
	}

	@Override
	public Optional<UserDTO> deleteUserById(HashMap<String, Object> param) {
		Optional<UserDTO> currentUser = userStore.findById((Long)param.get("user_id"));
		if( !currentUser.isPresent() ) {
			throw new BizException(BizStatusCode.USER_NOT_FOUND);		
		}
		userStore.deleteById((Long)param.get("userDTO"));
		return currentUser;
	}


}
