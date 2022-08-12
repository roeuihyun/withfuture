/*=================================================================================
 *                        Copyright(c) 2022 WithFuture
 *
 * Project                : withfuture
 * Source File Name       : com.roeuihyun.withfuture.enums.BizStatusCode
 * Description            :
 * Author                 : 노의현
 * Version                : 1.0.0
 * Created Date           : 2022.08.01
 * Updated Date           : 2022.08.01
 * Last Modifier          : 노의현
 * Updated Contents       : 
 * 2022.08.01 최초 작성
 *===============================================================================*/
package com.roeuihyun.withfuture.enums;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BizStatusCode implements StatusCode {

	USER_NOT_FOUND(HttpStatus.OK, 20100, "USER 검색 실패"),
	ORDER_NOT_FOUND(HttpStatus.OK, 20200, "주문 검색 실패"),
	CMCD_INFO_NOT_FOUND(HttpStatus.OK, 20300, "공통코드 정보 검색 실패"),

	USER_EXIST(HttpStatus.OK, 30100, "USER 입력 대상 존재"),
	ORDER_EXIST(HttpStatus.OK, 30200, "주문 입력 대상 존재"),
	CMCD_INFO_EXIST(HttpStatus.OK, 30300, "공통코드 정보 입력 대상 존재"),

	USER_INSERT_ERROR(HttpStatus.OK, 40100, "USER 입력 실패"),
	ORDER_INSERT_ERROR(HttpStatus.OK, 40200, "주문 입력 실패"),
	CMCD_INFO_INSERT_ERROR(HttpStatus.OK, 40300, "공통코드 정보 입력 실패"),

	USER_UPDATE_ERROR(HttpStatus.OK, 50100, "USER 수정 실패"),
	ORDER_UPDATE_ERROR(HttpStatus.OK, 50200, "주문 수정 실패"),
	CMCD_INFO_UPDATE_ERROR(HttpStatus.OK, 50300, "공통코드 정보 수정 실패"),

	USER_DELETE_ERROR(HttpStatus.OK, 60100, "USER 삭제 실패"),
	ORDER_DELETE_ERROR(HttpStatus.OK, 60200, "주문 삭제 실패"),
	CMCD_INFO_DELETE_ERROR(HttpStatus.OK, 60300, "공통코드 정보 삭제 실패"),
	;

    private final HttpStatus httpStatus;
    private final int biz_code;
    private final String message;
    
}