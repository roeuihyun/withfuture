/*=================================================================================
 *                        Copyright(c) 2022 WithFuture
 *
 * Project                : withfuture
 * Source File Name       : com.roeuihyun.withfuture.dto.UserDTO
 * Description            :
 * Author                 : 노의현
 * Version                : 1.0.0
 * Created Date           : 2022.08.01
 * Updated Date           : 2022.08.01
 * Last Modifier          : 노의현
 * Updated Contents       : 
 * 2022.08.01 최초 작성
 *===============================================================================*/
package com.roeuihyun.withfuture.dto;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicInsert // insert 되기 전에 엔티티에 설정된 컬럼 정보 중 null이 아닌 컬럼만을 이용하여 동적 insert 쿼리를 생성
@DynamicUpdate // 엔티티 update 할 때, 변경된 컬럼정보만을 이용하여 동적 쿼리를 생성
@Table(name = "user") // 테이블명과 클래스명이 다를경우
public class UserDTO {
	
	@Id
	@Type(type = "uuid-char")  
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "user_id", dataType = "string", required = true)  
	private UUID user_id;
	
	@Column(nullable = false) 
	@ApiModelProperty(value = "user_name", dataType = "string", required = false)
	private String user_name;
	
	@Column(nullable = false) 
	@ApiModelProperty(value = "user_email", dataType = "string", required = false)
	private String user_email;
	
	@Column(nullable = false) 
	@ApiModelProperty(value = "user_addr", dataType = "string", required = false)
	private String user_addr;
	
}
