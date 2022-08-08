package com.roeuihyun.withfuture.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roeuihyun.withfuture.entity.UserEO;
import com.roeuihyun.withfuture.service.UserService;
import com.roeuihyun.withfuture.store.UserStore;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@EnableAutoConfiguration
//@ExtendWith(SpringExtension.class) 5 버전
@RunWith(SpringRunner.class)
//@SpringBootTest(classes= {UserRestController.class}) // 테스트 대상 클래스 등록
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WebMvcTest(UserRestController.class)
@AutoConfigureMockMvc
@WebAppConfiguration
public class UserRestControllerTest {
	
    private final String BASE_URL = "/Users";
    
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;
    
    @MockBean
    UserService userService;

    @MockBean
    UserStore userStore;

    @Autowired
    WebApplicationContext ctx;
    
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                				 .addFilter(new CharacterEncodingFilter("UTF-8", true)) // 2.2 버전 이후 mock 객체에서 한글 인코딩 처리해야함. -> 필터추가
                				 .alwaysDo(print())
                				 .build();
    }
    
    @Order(1)
    @DisplayName("사용자 입력")
	@Test
	public void insertUser() throws Exception {
    	UserEO userEO = new UserEO();
    	userEO.setUser_id(1L);
    	userEO.setUser_name("userTest");
    	userEO.setUser_email("test@test.com");
    	userEO.setUser_addr("test 주소");
        mockMvc.perform(
        		post(BASE_URL)
		        .accept(MediaTypes.HAL_JSON_VALUE)
		        .contentType(MediaTypes.HAL_JSON_VALUE)
		        .content(objectMapper.writeValueAsString(userEO))
                )
		        .andDo(print())
		        .andExpect(status().isCreated())
		        .andReturn()
                ;
	}
	
    @Order(2)
    @DisplayName("사용자 전체 조회")
	@Test
	public void getAllUser() throws Exception {
    	MultiValueMap<String, String> paramMap =new LinkedMultiValueMap<>();
    	paramMap.add("pageNum", "1");
    	paramMap.add("pageSize", "10");
        mockMvc.perform(get(BASE_URL).params(paramMap)
                .accept(MediaTypes.HAL_JSON_VALUE)
                )
        		.andExpect(status().isOk())
                .andDo(print())
                ;
	}
    
    @Order(3)
    @DisplayName("사용자 1건 조회(OK)")
    @Test
    public void getUserById() throws Exception {
        mockMvc.perform(get(BASE_URL + "/{user_id}", 1L)
                .accept(MediaTypes.HAL_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }
    
    @Order(4)
    @DisplayName("사용자 1건 조회(NOF FOUND)")
    @Test
    public void getBoard_NotFound() throws Exception {
        mockMvc.perform(get(BASE_URL + "/{user_id}", -1L)
                .accept(MediaTypes.HAL_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}
