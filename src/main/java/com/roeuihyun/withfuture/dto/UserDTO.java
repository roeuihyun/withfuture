package com.roeuihyun.withfuture.dto;

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
public class UserDTO {
	@ApiModelProperty(value = "id", dataType = "string", required = true)
	private String id;
	@ApiModelProperty(value = "name", dataType = "string", required = false)
	private String name;
	
}
