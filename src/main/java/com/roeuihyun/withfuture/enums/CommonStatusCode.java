/*=================================================================================
 *                        Copyright(c) 2022 WithFuture
 *
 * Project                : withfuture
 * Source File Name       : com.roeuihyun.withfuture.enums.CommonStatusCode
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
public enum CommonStatusCode implements StatusCode {
	
	   CONTINUE(HttpStatus.CONTINUE, 10000,"Continue"),
	   SWITCHING_PROTOCOLS(HttpStatus.SWITCHING_PROTOCOLS, 10000,"Switching Protocols"),
	   PROCESSING(HttpStatus.PROCESSING, 10000,"Processing"),
	   CHECKPOINT(HttpStatus.CHECKPOINT, 10000,"Checkpoint"),
	   OK(HttpStatus.OK, 10000,"OK"),
	   CREATED(HttpStatus.CREATED, 10000,"Created"),
	   ACCEPTED(HttpStatus.ACCEPTED, 10000,"Accepted"),
	   NON_AUTHORITATIVE_INFORMATION(HttpStatus.NON_AUTHORITATIVE_INFORMATION, 10000,"Non-Authoritative Information"),
	   NO_CONTENT(HttpStatus.NO_CONTENT, 10000,"No Content"),
	   RESET_CONTENT(HttpStatus.RESET_CONTENT, 10000,"Reset Content"),
	   PARTIAL_CONTENT(HttpStatus.PARTIAL_CONTENT, 10000,"Partial Content"),
	   MULTI_STATUS(HttpStatus.MULTI_STATUS, 10000,"Multi-Status"),
	   ALREADY_REPORTED(HttpStatus.ALREADY_REPORTED, 10000,"Already Reported"),
	   IM_USED(HttpStatus.IM_USED, 10000,"IM Used"),
	   MULTIPLE_CHOICES(HttpStatus.MULTIPLE_CHOICES, 10000,"Multiple Choices"),
	   MOVED_PERMANENTLY(HttpStatus.MOVED_PERMANENTLY, 10000,"Moved Permanently"),
	   FOUND(HttpStatus.FOUND, 10000,"Found"),
	   SEE_OTHER(HttpStatus.SEE_OTHER, 10000,"See Other"),
	   NOT_MODIFIED(HttpStatus.NOT_MODIFIED, 10000,"Not Modified"),
	   TEMPORARY_REDIRECT(HttpStatus.TEMPORARY_REDIRECT, 10000,"Temporary Redirect"),
	   PERMANENT_REDIRECT(HttpStatus.PERMANENT_REDIRECT, 10000,"Permanent Redirect"),
	   BAD_REQUEST(HttpStatus.BAD_REQUEST, 10000,"Bad Request"),
	   UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 10000,"Unauthorized"),
	   PAYMENT_REQUIRED(HttpStatus.PAYMENT_REQUIRED, 10000,"Payment Required"),
	   FORBIDDEN(HttpStatus.FORBIDDEN, 10000,"Forbidden"),
	   NOT_FOUND(HttpStatus.NOT_FOUND, 10000,"Not Found"),
	   METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, 10000,"Method Not Allowed"),
	   NOT_ACCEPTABLE(HttpStatus.NOT_ACCEPTABLE, 10000,"Not Acceptable"),
	   PROXY_AUTHENTICATION_REQUIRED(HttpStatus.PROXY_AUTHENTICATION_REQUIRED, 10000,"Proxy Authentication Required"),
	   REQUEST_TIMEOUT(HttpStatus.REQUEST_TIMEOUT, 10000,"Request Timeout"),
	   CONFLICT(HttpStatus.CONFLICT, 10000,"Conflict"),
	   GONE(HttpStatus.GONE, 10000,"Gone"),
	   LENGTH_REQUIRED(HttpStatus.LENGTH_REQUIRED, 10000,"Length Required"),
	   PRECONDITION_FAILED(HttpStatus.PRECONDITION_FAILED, 10000,"Precondition Failed"),
	   PAYLOAD_TOO_LARGE(HttpStatus.PAYLOAD_TOO_LARGE, 10000,"Payload Too Large"),
	   URI_TOO_LONG(HttpStatus.URI_TOO_LONG, 10000,"URI Too Long"),
	   UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, 10000,"Unsupported Media Type"),
	   REQUESTED_RANGE_NOT_SATISFIABLE(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE, 10000,"Requested range not satisfiable"),
	   EXPECTATION_FAILED(HttpStatus.EXPECTATION_FAILED, 10000,"Expectation Failed"),
	   I_AM_A_TEAPOT(HttpStatus.I_AM_A_TEAPOT, 10000,"I'm a teapot"),
	   UNPROCESSABLE_ENTITY(HttpStatus.UNPROCESSABLE_ENTITY, 10000,"Unprocessable Entity"),
	   LOCKED(HttpStatus.LOCKED, 10000,"Locked"),
	   FAILED_DEPENDENCY(HttpStatus.FAILED_DEPENDENCY, 10000,"Failed Dependency"),
	   TOO_EARLY(HttpStatus.TOO_EARLY, 10000,"Too Early"),
	   UPGRADE_REQUIRED(HttpStatus.UPGRADE_REQUIRED, 10000,"Upgrade Required"),
	   PRECONDITION_REQUIRED(HttpStatus.PRECONDITION_REQUIRED, 10000,"Precondition Required"),
	   TOO_MANY_REQUESTS(HttpStatus.TOO_MANY_REQUESTS, 10000,"Too Many Requests"),
	   REQUEST_HEADER_FIELDS_TOO_LARGE(HttpStatus.REQUEST_HEADER_FIELDS_TOO_LARGE, 10000,"Request Header Fields Too Large"),
	   UNAVAILABLE_FOR_LEGAL_REASONS(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS, 10000,"Unavailable For Legal Reasons"),
	   INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 10000,"Internal Server Error"),
	   NOT_IMPLEMENTED(HttpStatus.NOT_IMPLEMENTED, 10000,"Not Implemented"),
	   BAD_GATEWAY(HttpStatus.BAD_GATEWAY, 10000,"Bad Gateway"),
	   SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, 10000,"Service Unavailable"),
	   GATEWAY_TIMEOUT(HttpStatus.GATEWAY_TIMEOUT, 10000,"Gateway Timeout"),
	   HTTP_VERSION_NOT_SUPPORTED(HttpStatus.HTTP_VERSION_NOT_SUPPORTED, 10000,"HTTP Version not supported"),
	   VARIANT_ALSO_NEGOTIATES(HttpStatus.VARIANT_ALSO_NEGOTIATES, 10000,"Variant Also Negotiates"),
	   INSUFFICIENT_STORAGE(HttpStatus.INSUFFICIENT_STORAGE, 10000,"Insufficient Storage"),
	   LOOP_DETECTED(HttpStatus.LOOP_DETECTED, 10000,"Loop Detected"),
	   BANDWIDTH_LIMIT_EXCEEDED(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED, 10000,"Bandwidth Limit Exceeded"),
	   NOT_EXTENDED(HttpStatus.NOT_EXTENDED, 10000,"Not Extended"),
	   NETWORK_AUTHENTICATION_REQUIRED(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED, 10000,"Network Authentication Required"),
	   ;

	private final HttpStatus httpStatus;
	private final int biz_code;
	private final String message;
    
}