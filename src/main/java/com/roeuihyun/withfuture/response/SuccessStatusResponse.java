/*=================================================================================
 *                        Copyright(c) 2022 WithFuture
 *
 * Project                : withfuture
 * Source File Name       : com.roeuihyun.withfuture.response.SuccessStatusResponse
 * Description            :
 * Author                 : 노의현
 * Version                : 1.0.0
 * Created Date           : 2022.08.01
 * Updated Date           : 2022.08.01
 * Last Modifier          : 노의현
 * Updated Contents       : 
 * 2022.08.01 최초 작성
 *===============================================================================*/
package com.roeuihyun.withfuture.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class SuccessStatusResponse {

//	@ApiModelProperty(value ="HttpStatusCode", dataType = "String")
//  private final String http_code;
	@ApiModelProperty(value ="비지니스 코드", dataType = "string")
	private final int biz_code;
	@ApiModelProperty(value ="메시지", dataType = "string")
	private final String message;
	@ApiModelProperty(value ="json 결과값", dataType = "string")
	private final Object result;
    
}