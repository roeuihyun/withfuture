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

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.roeuihyun.withfuture.entity.UserEO;
import com.roeuihyun.withfuture.enums.BizStatusCode;
import com.roeuihyun.withfuture.exception.BizException;
import com.roeuihyun.withfuture.store.UserStore;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserStore userStore;
	
	@Override
	@Transactional
	public UserEO insertUser(HashMap<String, Object> param) {
		Optional<UserEO> currentUser = userStore.findById((Long)param.get("user_id"));
		if( currentUser.isPresent() ) {
			throw new BizException(BizStatusCode.USER_EXIST);
		}
		userStore.save((UserEO)param.get("userEO"));
		return userStore.findById((Long)param.get("user_id")).get();
	}

	@Override
	public List<UserEO> getAllUser() {
		return (List<UserEO>) userStore.findAll();
	}

	@Override
	public UserEO getUserById(HashMap<String, Object> param) {
		Optional<UserEO> currentUser = userStore.findById((Long)param.get("user_id"));
		if( !currentUser.isPresent() ) {
			throw new BizException(BizStatusCode.USER_NOT_FOUND);		
		}
		return currentUser.get();
	}

	@Override
	@Transactional
	public UserEO putUser(HashMap<String, Object> param) {
		Optional<UserEO> currentUser = userStore.findById((Long)param.get("user_id"));
		if( !currentUser.isPresent() ) {
			throw new BizException(BizStatusCode.USER_NOT_FOUND);		
		}
		UserEO updateParam = (UserEO)param.get("userEO");
		UserEO updateReturn = currentUser.get();
		updateReturn.setUser_id(updateParam.getUser_id());
		updateReturn.setUser_name(updateParam.getUser_name());
		updateReturn.setUser_email(updateParam.getUser_email());
		updateReturn.setUser_addr(updateParam.getUser_addr());
		return updateReturn;
	}

	@Override
	@Transactional
	public UserEO deleteUserById(HashMap<String, Object> param) {
		Optional<UserEO> currentUser = userStore.findById((Long)param.get("user_id"));
		if( !currentUser.isPresent() ) {
			throw new BizException(BizStatusCode.USER_NOT_FOUND);		
		}
		userStore.deleteById((Long)param.get("user_id"));
		return currentUser.get();
	}


}
