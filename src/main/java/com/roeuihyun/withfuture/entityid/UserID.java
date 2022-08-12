package com.roeuihyun.withfuture.entityid;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserID implements Serializable{
	
	private long user_id;
	
}
