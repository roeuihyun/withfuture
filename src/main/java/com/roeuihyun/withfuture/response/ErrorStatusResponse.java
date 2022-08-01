/*=================================================================================
 *                        Copyright(c) 2022 WithFuture
 *
 * Project                : withfuture
 * Source File Name       : com.roeuihyun.withfuture.response.ErrorStatusResponse
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

import java.util.List;

import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ErrorStatusResponse {
	
//	@ApiModelProperty(value ="HttpStatusCode", dataType = "String")
//    private final String http_code;
	@ApiModelProperty(value ="비지니스 코드", dataType = "string")
    private final int biz_code;
	@ApiModelProperty(value ="메시지", dataType = "string")
    private final String message;
	@ApiModelProperty(value ="json 결과값", dataType = "string")
    private final Object result;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<Validation> errors;

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Validation {
    
        private final String field;
        private final String message;

        public static Validation of(final FieldError fieldError) {
            return Validation.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();
        }
    }
}