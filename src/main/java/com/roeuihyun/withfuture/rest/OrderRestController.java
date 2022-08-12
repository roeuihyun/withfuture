/*=================================================================================
 *                        Copyright(c) 2022 WithFuture
 *
 * Project                : withfuture
 * Source File Name       : com.roeuihyun.withfuture.rest.OrderRestController
 * Description            :
 * Author                 : 노의현
 * Version                : 1.0.0
 * Created Date           : 2022.08.01
 * Updated Date           : 2022.08.01
 * Last Modifier          : 노의현
 * Updated Contents       : 
 * 2022.08.01 최초 작성
 *===============================================================================*/
package com.roeuihyun.withfuture.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.HashMap;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.roeuihyun.withfuture.entity.OrderEO;
import com.roeuihyun.withfuture.entityid.OrderID;
import com.roeuihyun.withfuture.enums.CommonStatusCode;
import com.roeuihyun.withfuture.response.SuccessStatusResponse;
import com.roeuihyun.withfuture.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api(value = "주문 API" , tags = {"주문 API"} )
@RestController
@RequestMapping("/Orders")
@RequiredArgsConstructor
public class OrderRestController {
	
	private final OrderService orderService;
	private int defaultPageNum = 1;
	private int defaultPageSize = 10;
	
	@ApiOperation(value = "주문 추가", notes = "주문 하나를 추가합니다.")
	@PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
	@ResponseBody
	public EntityModel<SuccessStatusResponse> insertOrder(@RequestBody OrderEO orderEO) {
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("order_id", orderEO.getOrder_id());
		param.put("user_id", orderEO.getUser_id());
		param.put("orderID", new OrderID(orderEO.getOrder_id(), orderEO.getUser_id()) );
		param.put("orderEO", orderEO);
		@SuppressWarnings("deprecation")
		EntityModel<SuccessStatusResponse> model = new EntityModel<SuccessStatusResponse>(SuccessStatusResponse.builder()
		        .biz_code(CommonStatusCode.OK.getBiz_code())
		        .message(CommonStatusCode.OK.getMessage())
		        .result(orderService.insertOrder(param))
		        .build());
		model.add( linkTo( methodOn( this.getClass() ).insertOrder( orderEO ) ).withRel("self") );
		model.add( linkTo( methodOn( this.getClass() ).getOrderById( orderEO.getOrder_id(), orderEO.getUser_id() ) ).withRel("detail-order") );
		return model;
	}
	
	@ApiOperation(value = "주문 전체 조회", notes = "주문 전체를 조회합니다.")
	@GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
	@ResponseBody
	public EntityModel<SuccessStatusResponse> getAllOrder(
			@ApiParam(value = "PagingNumber", required = true, example = "1", defaultValue = "1")
			@RequestParam int pageNum,
			@ApiParam(value = "PagingSize", required = true, example = "10", defaultValue = "10")
			@RequestParam int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		@SuppressWarnings("deprecation")
		EntityModel<SuccessStatusResponse> model = new EntityModel<SuccessStatusResponse>(SuccessStatusResponse.builder()
		        .biz_code(CommonStatusCode.OK.getBiz_code())
		        .message(CommonStatusCode.OK.getMessage())
		        .result(PageInfo.of(orderService.getAllOrder()))
		        .build());
		model.add( linkTo( methodOn(this.getClass() ).getAllOrder(pageNum,pageSize) ).withRel("self") );
		return model;
	}
	
	@ApiOperation(value = "주문 조회", notes = "주문 하나를 조회합니다.")
	@GetMapping(value="/{order_id}/{user_id}", produces = MediaTypes.HAL_JSON_VALUE)
	@ResponseBody
	public EntityModel<SuccessStatusResponse> getOrderById(@PathVariable Long order_id, @PathVariable Long user_id) {
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("order_id", order_id);
		param.put("orderID", new OrderID(order_id, user_id) );
		@SuppressWarnings("deprecation")
		EntityModel<SuccessStatusResponse> model = new EntityModel<SuccessStatusResponse>(SuccessStatusResponse.builder()
		        .biz_code(CommonStatusCode.OK.getBiz_code())
		        .message(CommonStatusCode.OK.getMessage())
		        .result(orderService.getOrderById(param))
		        .build());
		model.add( linkTo( methodOn( this.getClass() ).getOrderById( order_id, user_id ) ).withRel("self") );
		model.add( linkTo( methodOn( this.getClass() ).getAllOrder( defaultPageNum , defaultPageSize ) ).withRel("all-order") );
		model.add( linkTo( methodOn( this.getClass() ).putOrder( orderService.getOrderById(param) ) ).withRel("update-order") );
		model.add( linkTo( methodOn( this.getClass() ).deleteOrderById( order_id, user_id ) ).withRel("delete stdword"));
		return model;
	}
	
	@ApiOperation(value = "주문 수정", notes = "주문 하나를 수정합니다.")
	@PutMapping(produces = MediaTypes.HAL_JSON_VALUE)
	@ResponseBody
	public EntityModel<SuccessStatusResponse> putOrder( @RequestBody OrderEO orderEO) {
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("order_id", orderEO.getOrder_id());
		param.put("user_id", orderEO.getUser_id());
		param.put("orderID", new OrderID(orderEO.getOrder_id(), orderEO.getUser_id()) );
		param.put("orderEO", orderEO);
		@SuppressWarnings("deprecation")
		EntityModel<SuccessStatusResponse> model = new EntityModel<SuccessStatusResponse>(SuccessStatusResponse.builder()
		        .biz_code(CommonStatusCode.OK.getBiz_code())
		        .message(CommonStatusCode.OK.getMessage())
		        .result(orderService.putOrder(param))
		        .build());
		model.add( linkTo( methodOn( this.getClass() ).putOrder( orderEO ) ).withRel("self") );
		model.add( linkTo( methodOn( this.getClass() ).getAllOrder( defaultPageNum , defaultPageSize ) ).withRel("all-order") );
		model.add( linkTo( methodOn( this.getClass() ).getOrderById( orderEO.getOrder_id(), orderEO.getUser_id() ) ).withRel("detail-order") );
		model.add( linkTo( methodOn( this.getClass() ).deleteOrderById( orderEO.getOrder_id(), orderEO.getUser_id() ) ).withRel("delete-order"));
		return model;
	}
	
	@ApiOperation(value = "주문 삭제", notes = "주문 하나를 삭제합니다.")
	@DeleteMapping(value="/{order_id}/{user_id}", produces = MediaTypes.HAL_JSON_VALUE)
	@ResponseBody
	public EntityModel<SuccessStatusResponse> deleteOrderById(@PathVariable Long order_id, @PathVariable Long user_id) {
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("order_id", order_id);
		param.put("orderID", new OrderID(order_id, user_id) );
		@SuppressWarnings("deprecation")
		EntityModel<SuccessStatusResponse> model = new EntityModel<SuccessStatusResponse>(SuccessStatusResponse.builder()
		        .biz_code(CommonStatusCode.OK.getBiz_code())
		        .message(CommonStatusCode.OK.getMessage())
		        .result(orderService.deleteOrderById(param))
		        .build());
		model.add( linkTo( methodOn(this.getClass() ).deleteOrderById( order_id, user_id ) ).withRel("self") );
		model.add( linkTo( methodOn( this.getClass() ).getAllOrder( defaultPageNum , defaultPageSize ) ).withRel("all-order") );
		return model;
	}
}
