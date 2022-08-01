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
import java.util.Optional;

import com.roeuihyun.withfuture.dto.UserDTO;

public interface UserService {

	Optional<UserDTO> insertUser(HashMap<String, Object> param);

	List<UserDTO> getAllUser();

	Optional<UserDTO> getUserById(HashMap<String, Object> param);

	Optional<UserDTO> putUser(HashMap<String, Object> param);

	Optional<UserDTO> deleteUserById(HashMap<String, Object> param);
	

}
