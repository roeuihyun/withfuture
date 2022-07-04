package com.roeuihyun.withfuture.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.roeuihyun.withfuture.dto.UserDTO;
import com.roeuihyun.withfuture.response.CommonStatusCode;
import com.roeuihyun.withfuture.response.StatusResponse;
import com.roeuihyun.withfuture.service.WithfutureService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/User")
@RequiredArgsConstructor
public class WithfutureRestController {
	
	private final WithfutureService withfutureService;
	@ApiOperation(value="User 정보 Return", notes="User 정보 Return Notes")
	@ApiResponses({
	        @ApiResponse(code = 200, message = "API 정상 작동"),
	        @ApiResponse(code = 500, message = "서버 에러")
	})
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<StatusResponse> getUser(@PathVariable String id){
//		return ResponseEntity.ok(withfutureService.getUser());
		
return ResponseEntity.status(CommonStatusCode.OK.getHttpStatus())
        .body(StatusResponse.builder()
        .code(CommonStatusCode.OK.name())
        .message(CommonStatusCode.OK.getMessage())
        .result(withfutureService.getUser())
        .build());
		
	}
	
	@ApiOperation(value="전체 User 정보 Return", notes="전체 User 정보 Return Notes")
	@ApiResponses({
	        @ApiResponse(code = 200, message = "API 정상 작동"),
	        @ApiResponse(code = 500, message = "서버 에러")
	})
	@GetMapping("/")
	@ResponseBody
	public ResponseEntity<StatusResponse> getAllUser(){
//		return ResponseEntity.ok(withfutureService.getAllUser());
		return ResponseEntity.status(CommonStatusCode.OK.getHttpStatus())
		        .body(StatusResponse.builder()
		        .code(CommonStatusCode.OK.name())
		        .message(CommonStatusCode.OK.getMessage())
		        .result(withfutureService.getAllUser())
		        .build());
	}
	
	@ApiOperation(value="전체 User 정보 Return Test", notes="전체 User 정보 Return Notes Test")
	@ApiResponses({
	        @ApiResponse(code = 200, message = "API 정상 작동"),
	        @ApiResponse(code = 500, message = "서버 에러")
	})
	@GetMapping("/test")
	@ResponseBody
	public ResponseEntity<StatusResponse> getTestUser() {
//		return ResponseEntity.ok(withfutureService.getTestUser());
		return ResponseEntity.status(CommonStatusCode.OK.getHttpStatus())
		        .body(StatusResponse.builder()
		        .code(CommonStatusCode.OK.name())
		        .message(CommonStatusCode.OK.getMessage())
		        .result(withfutureService.getTestUser())
		        .build());
	}

}
