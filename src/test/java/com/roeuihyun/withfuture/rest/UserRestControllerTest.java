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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
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
//@SpringBootTest(classes= {StdWordController.class}) // 테스트 대상 클래스 등록
//@WebMvcTest(StdWordController.class)
//@AutoConfigureMockMvc
//@WebAppConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("local")
public class UserRestControllerTest {
	
    private final String BASE_URL = "/Users";
    
    @Autowired
    MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext ctx;
    
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

//    @Order(1)
//    @DisplayName("표준 단어 전체 조회")
//	@Test
//	void getAllStdWord() throws Exception {
//    	MultiValueMap<String, String> paramMap =new LinkedMultiValueMap<>();
//    	paramMap.add("pageNum", "1");
//    	paramMap.add("pageSize", "10");
//        mockMvc.perform(
//        		get(BASE_URL)
//        		.characterEncoding("UTF-8")
//                .contentType(MediaTypes.HAL_JSON_VALUE)
//        		.params(paramMap)
//        		)
//				.andDo(print())
//				;
//		
//	}
//	
//    @Order(2)
//    @DisplayName("표준 단어 추가")
//    @Transactional
//	@Test
//	void insertStdWord() throws Exception {
//    	StdWordEO stdWordEO =  new StdWordEO();
//    	stdWordEO.setWord_uuid(word_uuid);
//    	stdWordEO.setWord_dictn_uuid(termn_dictn_uuid);
//    	stdWordEO.setWord_nm("가동");
//    	stdWordEO.setWord_eng_nm("Operating");
//    	stdWordEO.setWord_abbr_nm("OPRTNG");
//    	stdWordEO.setWord_expl("稼動. 사람이나 기계 따위가 움직여 일함. 또는 기계 따위를 움직여 일하게 함");
//    	stdWordEO.setWord_clsf_cd("modifier");
//    	stdWordEO.setFrst_crt_dt("20220704000000");
//    	stdWordEO.setFrst_crtr("system");
//    	stdWordEO.setLast_mdfcn_dt("20220704000000");
//    	stdWordEO.setLast_mdfr("system");
//    	stdWordEO.setDmnd_dt("20220704000000");
//    	stdWordEO.setDel_yn(null);
//		HashMap<String,Object> param = new HashMap<String,Object>();
//		param.put("word_uuid", stdWordEO.getWord_uuid());
//		param.put("stdWordEO", stdWordEO);
//		
//		mockMvc.perform(
//				post(BASE_URL)
//				.characterEncoding("UTF-8")
//				.contentType(MediaTypes.HAL_JSON_VALUE)
//				.content(objectMapper.writeValueAsString(param))
//			)
//			.andDo(print())
//			;
//		
//	}
//    
//    @Order(3)
//    @DisplayName("표준 단어 조회")
//	@Test
//	void getStdWordById() throws Exception {
//		
//        mockMvc.perform(
//        		get(BASE_URL+"/"+ word_uuid)
//        		.characterEncoding("UTF-8")
//                .contentType(MediaTypes.HAL_JSON_VALUE)
//    			)
//    			.andDo(print())
//    			;
//		
//	}
//    
//    @Order(4)
//    @DisplayName("표준 단어 수정")
//    @Transactional
//	@Test
//	void putStdWord() throws Exception {
//    	StdWordEO stdWordEO =  new StdWordEO();
//    	stdWordEO.setWord_uuid(word_uuid);
//    	stdWordEO.setWord_dictn_uuid(termn_dictn_uuid);
//    	stdWordEO.setWord_nm("가동 수정");
//    	stdWordEO.setWord_eng_nm("Operating 수정");
//    	stdWordEO.setWord_abbr_nm("OPRTNG 수정");
//    	stdWordEO.setWord_expl("稼動. 사람이나 기계 따위가 움직여 일함. 또는 기계 따위를 움직여 일하게 함 수정");
//    	stdWordEO.setWord_clsf_cd("modifier 수정");
//    	stdWordEO.setFrst_crt_dt("20220704000000");
//    	stdWordEO.setFrst_crtr("system");
//    	stdWordEO.setLast_mdfcn_dt("20220704000000");
//    	stdWordEO.setLast_mdfr("system");
//    	stdWordEO.setDmnd_dt("20220704000000");
//    	stdWordEO.setDel_yn(null);
//		HashMap<String,Object> param = new HashMap<String,Object>();
//		param.put("word_uuid", stdWordEO.getWord_uuid());
//		param.put("stdWordEO", stdWordEO);
//		
//		mockMvc.perform(
//				put(BASE_URL)
//				.characterEncoding("UTF-8")
//				.contentType(MediaTypes.HAL_JSON_VALUE)
//				.content(objectMapper.writeValueAsString(param))
//			)
//			.andDo(print())
//			;
//		
//	}
//    
//    @Order(5)
//    @DisplayName("표준 단어 삭제")
//    @Transactional
//	@Test
//	void deleteStdWordById() throws Exception {
//		HashMap<String,Object> param = new HashMap<String,Object>();
//		param.put("word_uuid", word_uuid);
//		mockMvc.perform(
//				delete(BASE_URL)
//				.characterEncoding("UTF-8")
//				.contentType(MediaTypes.HAL_JSON_VALUE)
//				.content(objectMapper.writeValueAsString(param))
//			)
//			.andDo(print())
//			;
//		
//	}
    

}
