package com.roeuihyun.withfuture.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roeuihyun.withfuture.entity.OrderEO;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@EnableAutoConfiguration
//@ExtendWith({SpringExtension.class}) //5 버전
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes= {OrderController.class}) // 테스트 대상 클래스 등록
//@WebMvcTest(OrderController.class)
//@AutoConfigureMockMvc
//@WebAppConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("default")
public class OrderRestControllerTest {
	
    private final String BASE_URL = "/Orders";
    
    MockMvc mockMvc;

    private Long order_id = new Long("111111");
    private Long user_id = new Long("123123");

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext ctx;
    
    @BeforeEach
    public void beforeEach() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                				 .addFilter(new CharacterEncodingFilter("UTF-8", true)) // 2.2 버전 이후 mock 객체에서 한글 인코딩 처리해야함. -> 필터추가
                				 .alwaysDo(print())
                				 .build();
    }
    
    @AfterEach
    public void afterEach() {
    	
    }

    @Order(1)
    @DisplayName("주문 추가")
    @Transactional
    @Rollback(false)
	@Test
	void insertOrder() throws Exception {
        order_id = new Long("111111");
        user_id = new Long("123123");
    	OrderEO orderEO = new OrderEO();
    	orderEO.setOrder_id(order_id);
    	orderEO.setUser_id(user_id);
    	orderEO.setOrder_name("orderTest");
    	orderEO.setOrder_desc("orderTest desc");
    	orderEO.setOrder_qty("111");
		
		mockMvc.perform(
				post(BASE_URL)
				.characterEncoding("UTF-8")
				.contentType(MediaTypes.HAL_JSON_VALUE)
				.content(objectMapper.writeValueAsString(orderEO))
			)
			;
		
	}
    
    @Order(2)
    @DisplayName("주문 전체 조회")
	@Test
	void getAllOrder() throws Exception {
        order_id = new Long("111111");
        user_id = new Long("123123");
    	MultiValueMap<String, String> paramMap =new LinkedMultiValueMap<>();
    	paramMap.add("pageNum", "1");
    	paramMap.add("pageSize", "10");
        mockMvc.perform(
        		get(BASE_URL)
        		.characterEncoding("UTF-8")
                .contentType(MediaTypes.HAL_JSON_VALUE)
        		.params(paramMap)
        		)
				;
		
	}
    
    @Order(3)
    @DisplayName("주문 조회")
	@Test
	void getOrderById() throws Exception {
        order_id = new Long("111111");
        user_id = new Long("123123");
        mockMvc.perform(
        		get(BASE_URL+"/"+ order_id+"/"+ user_id)
        		.characterEncoding("UTF-8")
                .contentType(MediaTypes.HAL_JSON_VALUE)
    			)
    			;
		
	}
    
    @Order(4)
    @DisplayName("주문 수정")
    @Transactional
    @Rollback(false)
	@Test
	void putOrder() throws Exception {
        order_id = new Long("111111");
        user_id = new Long("123123");
    	OrderEO orderEO = new OrderEO();
    	orderEO.setOrder_id(order_id);
    	orderEO.setUser_id(user_id);
    	orderEO.setOrder_name("orderTest 수정수정");
    	orderEO.setOrder_desc("orderTest desc 수정");
    	orderEO.setOrder_qty("111");
		
		mockMvc.perform(
				put(BASE_URL)
				.characterEncoding("UTF-8")
				.contentType(MediaTypes.HAL_JSON_VALUE)
				.content(objectMapper.writeValueAsString(orderEO))
			)
			;
		
	}
    
    @Order(5)
    @DisplayName("주문 삭제")
    @Transactional
    @Rollback(false)
	@Test
	void deleteOrderById() throws Exception {
        order_id = new Long("111111");
        user_id = new Long("123123");
		mockMvc.perform(
				delete(BASE_URL+"/"+ order_id+"/"+ user_id)
				.characterEncoding("UTF-8")
				.contentType(MediaTypes.HAL_JSON_VALUE)
			)
			;
		
	}
    

}
