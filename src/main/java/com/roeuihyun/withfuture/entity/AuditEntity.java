package com.roeuihyun.withfuture.entity;

import org.springframework.data.domain.Persistable;

public interface AuditEntity extends Persistable<Object>{
	
    public void modifyIsNew();
    
}
