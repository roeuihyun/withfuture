package com.roeuihyun.withfuture.dto;

import org.springframework.data.domain.Persistable;

public interface AuditEntity extends Persistable<Object>{
	
    public void modifyIsNew();
    
}
