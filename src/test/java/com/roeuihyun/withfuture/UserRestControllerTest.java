package com.roeuihyun.withfuture;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roeuihyun.withfuture.rest.UserRestController;
import com.roeuihyun.withfuture.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableAutoConfiguration
@SpringBootTest(classes= {UserRestController.class}) // 테스트 대상 클래스 등록 
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)	
@AutoConfigureMockMvc
@WebAppConfiguration
public class UserRestControllerTest {
	
    private final String BASE_URL = "/Users";
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;

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
    @DisplayName("사용자 전체 조회")
	@Test
	public void getAllUser() throws Exception {
        mockMvc.perform(get(BASE_URL)
//                .accept(MediaTypes.HAL_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().isOk());
//		.andExpect(status().isOk())
//		.andExpect((ResultMatcher) content().string("hello"))
		
	}

}
