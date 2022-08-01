/*=================================================================================
 *                        Copyright(c) 2022 WithFuture
 *
 * Project                : withfuture
 * Source File Name       : com.roeuihyun.withfuture.rest.UserRestController
 * Description            :
 * Author                 : 노의현
 * Version                : 1.0.0
 * Created Date           : 2022.08.01
 * Updated Date           : 2022.08.01
 * Last Modifier          : 노의현
 * Updated Contents       : 
 * 2022.08.01 최초 작성
 *===============================================================================*/
package com.roeuihyun.withfuture.rest;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.roeuihyun.withfuture.dto.UserDTO;
import com.roeuihyun.withfuture.enums.CommonStatusCode;
import com.roeuihyun.withfuture.response.SuccessStatusResponse;
import com.roeuihyun.withfuture.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/User")
@RequiredArgsConstructor
public class UserRestController {
	
	private final UserService userService;
	
	@ApiOperation(value = "USER 추가", notes = "USER 하나를 추가합니다.")
	@PostMapping(value="")
	@ResponseBody
	public ResponseEntity<SuccessStatusResponse> insertUser(@RequestBody UserDTO userDTO) {
		HashMap<String,Object> param = new HashMap<String,Object>();
		System.out.println(userDTO);
		System.out.println(userDTO.getUser_id());
		System.out.println(userDTO.getUser_name());
		System.out.println(userDTO.getUser_addr());
		System.out.println(userDTO.getUser_email());
		param.put("user_id", userDTO.getUser_id());
		param.put("userDTO", userDTO);
		return ResponseEntity.status(CommonStatusCode.OK.getHttpStatus())
		        .body(SuccessStatusResponse.builder()
//								        .http_code(CommonStatusCode.OK.getHttpStatus().toString())
								        .biz_code(CommonStatusCode.OK.getBiz_code())
								        .message(CommonStatusCode.OK.getMessage())
								        .result(userService.insertUser(param))
								        .build());
	}
	
	@ApiOperation(value = "USER 전체 조회", notes = "USER 전체를 조회합니다.")
	@GetMapping(value="")
	@ResponseBody
	public ResponseEntity<SuccessStatusResponse> getAllUser(
			@ApiParam(value = "PagingNumber", required = true, example = "1", defaultValue = "1")
			@RequestParam int pageNum,
			@ApiParam(value = "PagingSize", required = true, example = "10", defaultValue = "10")
			@RequestParam int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return ResponseEntity.status(CommonStatusCode.OK.getHttpStatus())
		        .body(SuccessStatusResponse.builder()
//								        .http_code(CommonStatusCode.OK.getHttpStatus().toString())
								        .biz_code(CommonStatusCode.OK.getBiz_code())
								        .message(CommonStatusCode.OK.getMessage())
								        .result(PageInfo.of(userService.getAllUser()))
								        .build());
	}
	
	@ApiOperation(value = "USER 조회", notes = "USER 하나를 조회합니다.")
	@GetMapping(value="/{user_id}")
	@ResponseBody
	public ResponseEntity<SuccessStatusResponse> getUserById(@PathVariable Long user_id) {
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("user_id", user_id);
		return ResponseEntity.status(CommonStatusCode.OK.getHttpStatus())
		        .body(SuccessStatusResponse.builder()
//								        .http_code(CommonStatusCode.OK.getHttpStatus().toString())
								        .biz_code(CommonStatusCode.OK.getBiz_code())
								        .message(CommonStatusCode.OK.getMessage())
								        .result(userService.getUserById(param))
								        .build());
	}
	
	@ApiOperation(value = "USER 수정", notes = "USER 하나를 수정합니다.")
	@PutMapping(value="")
	@ResponseBody
	public ResponseEntity<SuccessStatusResponse> putUser( @RequestBody UserDTO userDTO) {
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("user_id", userDTO.getUser_id());
		param.put("userDTO", userDTO);
		return ResponseEntity.status(CommonStatusCode.OK.getHttpStatus())
		        .body(SuccessStatusResponse.builder()
//								        .http_code(CommonStatusCode.OK.getHttpStatus().toString())
								        .biz_code(CommonStatusCode.OK.getBiz_code())
								        .message(CommonStatusCode.OK.getMessage())
								        .result(userService.putUser(param))
								        .build());
	}
	
	@ApiOperation(value = "USER 삭제", notes = "USER 하나를 삭제합니다.")
	@DeleteMapping(value="/{user_id}")
	@ResponseBody
	public ResponseEntity<SuccessStatusResponse> deleteUserById(@PathVariable Long user_id) {
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("user_id", user_id);
		return ResponseEntity.status(CommonStatusCode.OK.getHttpStatus())
		        .body(SuccessStatusResponse.builder()
//								        .http_code(CommonStatusCode.OK.getHttpStatus().toString())
								        .biz_code(CommonStatusCode.OK.getBiz_code())
								        .message(CommonStatusCode.OK.getMessage())
								        .result(userService.deleteUserById(param))
								        .build());
	}
}
