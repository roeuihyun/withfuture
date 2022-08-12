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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.HashMap;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
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
import com.roeuihyun.withfuture.entity.UserEO;
import com.roeuihyun.withfuture.entityid.UserID;
import com.roeuihyun.withfuture.enums.CommonStatusCode;
import com.roeuihyun.withfuture.response.SuccessStatusResponse;
import com.roeuihyun.withfuture.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api(value = "사용자 API" , tags = {"사용자 API"} )
@RestController
@RequestMapping("/Users")
@RequiredArgsConstructor
public class UserRestController {
	
	private final UserService userService;
	private int defaultPageNum = 1;
	private int defaultPageSize = 10;
	
	@ApiOperation(value = "USER 추가", notes = "USER 하나를 추가합니다.")
	@PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
	@ResponseBody
	public EntityModel<SuccessStatusResponse> insertUser(@RequestBody UserEO userEO) {
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("user_id", userEO.getUser_id());
		param.put("userID", new UserID(userEO.getUser_id()) );
		param.put("userEO", userEO);
		@SuppressWarnings("deprecation")
		EntityModel<SuccessStatusResponse> model = new EntityModel<SuccessStatusResponse>(SuccessStatusResponse.builder()
		        .biz_code(CommonStatusCode.OK.getBiz_code())
		        .message(CommonStatusCode.OK.getMessage())
		        .result(userService.insertUser(param))
		        .build());
		model.add( linkTo( methodOn( this.getClass() ).insertUser( userEO ) ).withRel("self") );
		model.add( linkTo( methodOn( this.getClass() ).getUserById( userEO.getUser_id() ) ).withRel("detail-user") );
		return model;
	}
	
	@ApiOperation(value = "USER 전체 조회", notes = "USER 전체를 조회합니다.")
	@GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
	@ResponseBody
	public EntityModel<SuccessStatusResponse> getAllUser(
			@ApiParam(value = "PagingNumber", required = true, example = "1", defaultValue = "1")
			@RequestParam int pageNum,
			@ApiParam(value = "PagingSize", required = true, example = "10", defaultValue = "10")
			@RequestParam int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		@SuppressWarnings("deprecation")
		EntityModel<SuccessStatusResponse> model = new EntityModel<SuccessStatusResponse>(SuccessStatusResponse.builder()
		        .biz_code(CommonStatusCode.OK.getBiz_code())
		        .message(CommonStatusCode.OK.getMessage())
		        .result(PageInfo.of(userService.getAllUser()))
		        .build());
		model.add( linkTo( methodOn(this.getClass() ).getAllUser(pageNum,pageSize) ).withRel("self") );
		return model;
	}
	
	@ApiOperation(value = "USER 조회", notes = "USER 하나를 조회합니다.")
	@GetMapping(value="/{user_id}", produces = MediaTypes.HAL_JSON_VALUE)
	@ResponseBody
	public EntityModel<SuccessStatusResponse> getUserById(@PathVariable Long user_id) {
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("user_id", user_id);
		param.put("userID", new UserID(user_id) );
		@SuppressWarnings("deprecation")
		EntityModel<SuccessStatusResponse> model = new EntityModel<SuccessStatusResponse>(SuccessStatusResponse.builder()
		        .biz_code(CommonStatusCode.OK.getBiz_code())
		        .message(CommonStatusCode.OK.getMessage())
		        .result(userService.getUserById(param))
		        .build());
		model.add( linkTo( methodOn( this.getClass() ).getUserById( user_id ) ).withRel("self") );
		model.add( linkTo( methodOn( this.getClass() ).getAllUser( defaultPageNum , defaultPageSize ) ).withRel("all-user") );
		model.add( linkTo( methodOn( this.getClass() ).putUser( userService.getUserById(param) ) ).withRel("update-user") );
		model.add( linkTo( methodOn( this.getClass() ).deleteUserById( user_id ) ).withRel("delete stdword"));
		return model;
	}
	
	@ApiOperation(value = "USER 수정", notes = "USER 하나를 수정합니다.")
	@PutMapping(produces = MediaTypes.HAL_JSON_VALUE)
	@ResponseBody
	public EntityModel<SuccessStatusResponse> putUser( @RequestBody UserEO userEO) {
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("user_id", userEO.getUser_id());
		param.put("userID", new UserID(userEO.getUser_id()) );
		param.put("userEO", userEO);
		@SuppressWarnings("deprecation")
		EntityModel<SuccessStatusResponse> model = new EntityModel<SuccessStatusResponse>(SuccessStatusResponse.builder()
		        .biz_code(CommonStatusCode.OK.getBiz_code())
		        .message(CommonStatusCode.OK.getMessage())
		        .result(userService.putUser(param))
		        .build());
		model.add( linkTo( methodOn( this.getClass() ).putUser( userEO ) ).withRel("self") );
		model.add( linkTo( methodOn( this.getClass() ).getAllUser( defaultPageNum , defaultPageSize ) ).withRel("all-user") );
		model.add( linkTo( methodOn( this.getClass() ).getUserById( userEO.getUser_id() ) ).withRel("detail-user") );
		model.add( linkTo( methodOn( this.getClass() ).deleteUserById( userEO.getUser_id() ) ).withRel("delete-user"));
		return model;
	}
	
	@ApiOperation(value = "USER 삭제", notes = "USER 하나를 삭제합니다.")
	@DeleteMapping(value="/{user_id}", produces = MediaTypes.HAL_JSON_VALUE)
	@ResponseBody
	public EntityModel<SuccessStatusResponse> deleteUserById(@PathVariable Long user_id) {
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("user_id", user_id);
		param.put("userID", new UserID(user_id) );
		@SuppressWarnings("deprecation")
		EntityModel<SuccessStatusResponse> model = new EntityModel<SuccessStatusResponse>(SuccessStatusResponse.builder()
		        .biz_code(CommonStatusCode.OK.getBiz_code())
		        .message(CommonStatusCode.OK.getMessage())
		        .result(userService.deleteUserById(param))
		        .build());
		model.add( linkTo( methodOn(this.getClass() ).deleteUserById( user_id ) ).withRel("self") );
		model.add( linkTo( methodOn( this.getClass() ).getAllUser( defaultPageNum , defaultPageSize ) ).withRel("all-user") );
		return model;
	}
}
