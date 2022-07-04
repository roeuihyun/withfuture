package com.roeuihyun.withfuture.dto;

import org.springframework.http.HttpStatus;

import com.roeuihyun.withfuture.response.ErrorCode;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO implements ErrorCode{
	@ApiModelProperty(value = "id", dataType = "string", required = true)
	private String id;
	@ApiModelProperty(value = "name", dataType = "string", required = false)
	private String name;
	@Override
	public HttpStatus getHttpStatus() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String name() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
