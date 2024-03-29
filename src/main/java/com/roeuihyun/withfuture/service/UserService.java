/*=================================================================================
 *                        Copyright(c) 2022 WithFuture
 *
 * Project                : withfuture
 * Source File Name       : com.roeuihyun.withfuture.service.UserService
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

import com.roeuihyun.withfuture.entity.UserEO;

public interface UserService {

	UserEO insertUser(HashMap<String, Object> param);

	List<UserEO> getAllUser();

	UserEO getUserById(HashMap<String, Object> param);

	UserEO putUser(HashMap<String, Object> param);

	UserEO deleteUserById(HashMap<String, Object> param);

}
