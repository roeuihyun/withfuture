/*=================================================================================
 *                        Copyright(c) 2022 WithFuture
 *
 * Project                : withfuture
 * Source File Name       : com.roeuihyun.withfuture.service.OrderServiceImpl
 * Description            :
 * Author                 : 노의현
 * Version                : 1.0.0
 * Created Date           : 2022.08.01
 * Updated Date           : 2022.08.01
 * Last Modifier          : 노의현
 * Updated Contents       : 
 * 2022.08.01 최초 작성
 *===============================================================================*/
package com.roeuihyun.withfuture.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.roeuihyun.withfuture.entity.OrderEO;
import com.roeuihyun.withfuture.entityid.OrderID;
import com.roeuihyun.withfuture.enums.BizStatusCode;
import com.roeuihyun.withfuture.exception.BizException;
import com.roeuihyun.withfuture.store.OrderStore;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
	
	private final OrderStore orderStore;
	
	@Override
	@Transactional
	public OrderEO insertOrder(HashMap<String, Object> param) {
		Optional<OrderEO> currentOrder = orderStore.findById( (OrderID)param.get("orderID") );
		if( currentOrder.isPresent() ) {
			throw new BizException(BizStatusCode.ORDER_EXIST);
		}
		orderStore.save((OrderEO)param.get("orderEO"));
		return orderStore.findById( (OrderID)param.get("orderID") ).get();
	}

	@Override
	public List<OrderEO> getAllOrder() {
		return (List<OrderEO>) orderStore.findAll();
	}

	@Override
	public OrderEO getOrderById(HashMap<String, Object> param) {
		Optional<OrderEO> currentOrder = orderStore.findById( (OrderID)param.get("orderID") );
		if( !currentOrder.isPresent() ) {
			throw new BizException(BizStatusCode.ORDER_NOT_FOUND);		
		}
		return currentOrder.get();
	}

	@Override
	@Transactional
	public OrderEO putOrder(HashMap<String, Object> param) {
		Optional<OrderEO> currentOrder = orderStore.findById( (OrderID)param.get("orderID") );
		if( !currentOrder.isPresent() ) {
			throw new BizException(BizStatusCode.ORDER_NOT_FOUND);		
		}
		OrderEO updateParam = (OrderEO)param.get("orderEO");
		OrderEO updateReturn = currentOrder.get();
		updateReturn.setOrder_id(updateParam.getOrder_id());
		updateReturn.setUser_id(updateParam.getUser_id());
		updateReturn.setOrder_name(updateParam.getOrder_name());
		updateReturn.setOrder_desc(updateParam.getOrder_desc());
		updateReturn.setOrder_qty(updateParam.getOrder_qty());
		return updateReturn;
	}

	@Override
	@Transactional
	public OrderEO deleteOrderById(HashMap<String, Object> param) {
		Optional<OrderEO> currentOrder = orderStore.findById( (OrderID)param.get("orderID") );
		if( !currentOrder.isPresent() ) {
			throw new BizException(BizStatusCode.ORDER_NOT_FOUND);		
		}
		orderStore.deleteById( (OrderID)param.get("orderID") );
		return currentOrder.get();
	}


}
