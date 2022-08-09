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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roeuihyun.withfuture.entity.UserEO;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@EnableAutoConfiguration
//@ExtendWith({SpringExtension.class}) //5 버전
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes= {UserController.class}) // 테스트 대상 클래스 등록
//@WebMvcTest(UserController.class)
//@AutoConfigureMockMvc
//@WebAppConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("local")
public class UserRestControllerTest {
	
    private final String BASE_URL = "/Users";
    
    MockMvc mockMvc;

//    private final Long user_id = 1L;
    private final Long user_id = Long.parseLong("111");

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
    @DisplayName("사용자 추가")
    @Transactional
    @Rollback(false)
	@Test
	void insertUser() throws Exception {
    	
    	UserEO userEO = new UserEO();
    	userEO.setUser_id(user_id);
    	userEO.setUser_name("userTest");
    	userEO.setUser_email("test@test.com");
    	userEO.setUser_addr("test 주소");
		
		mockMvc.perform(
				post(BASE_URL)
				.characterEncoding("UTF-8")
				.contentType(MediaTypes.HAL_JSON_VALUE)
				.content(objectMapper.writeValueAsString(userEO))
			)
			.andDo(print())
			;
		
	}
    
    @Order(2)
    @DisplayName("사용자 전체 조회")
	@Test
	void getAllUser() throws Exception {
    	
    	MultiValueMap<String, String> paramMap =new LinkedMultiValueMap<>();
    	paramMap.add("pageNum", "1");
    	paramMap.add("pageSize", "10");
        mockMvc.perform(
        		get(BASE_URL)
        		.characterEncoding("UTF-8")
                .contentType(MediaTypes.HAL_JSON_VALUE)
        		.params(paramMap)
        		)
				.andDo(print())
				;
		
	}
    
    @Order(3)
    @DisplayName("사용자 조회")
	@Test
	void getUserById() throws Exception {
		
        mockMvc.perform(
        		get(BASE_URL+"/"+ user_id)
        		.characterEncoding("UTF-8")
                .contentType(MediaTypes.HAL_JSON_VALUE)
    			)
    			.andDo(print())
    			;
		
	}
    
    @Order(4)
    @DisplayName("사용자 수정")
    @Transactional
    @Rollback(false)
	@Test
	void putUser() throws Exception {
    	
    	UserEO userEO = new UserEO();
    	userEO.setUser_id(user_id);
    	userEO.setUser_name("userTest 수정");
    	userEO.setUser_email("test@test.com 수정");
    	userEO.setUser_addr("test 주소 수정");
		
		mockMvc.perform(
				put(BASE_URL)
				.characterEncoding("UTF-8")
				.contentType(MediaTypes.HAL_JSON_VALUE)
				.content(objectMapper.writeValueAsString(userEO))
			)
			.andDo(print())
			;
		
	}
    
    @Order(5)
    @DisplayName("사용자 삭제")
    @Transactional
    @Rollback(false)
	@Test
	void deleteUserById() throws Exception {
		
		mockMvc.perform(
				delete(BASE_URL)
				.characterEncoding("UTF-8")
				.contentType(MediaTypes.HAL_JSON_VALUE)
				.content(objectMapper.writeValueAsString(user_id))
			)
			.andDo(print())
			;
		
	}
    

}
