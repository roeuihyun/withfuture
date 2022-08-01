/*=================================================================================
 *                        Copyright(c) 2022 WithFuture
 *
 * Project                : withMETAdata-boot
 * Source File Name       : com.withfuture.withmetadata.config.XssFilterResponseConfig
 * Description            : 출력시 Jackson 같은 Mapper를 통해 JSON 문자열로 Response에 담겨지므로, Mapper가 JSON 문자열을 생성할 때 XSS 방지 처리
 * Author                 : 남건우
 * Version                : 1.0.0
 * Created Date           : 2022.07.05
 * Updated Date           : 2022.07.05
 * Last Modifier          : 남건우
 * Updated Contents       : 
 * 2022.07.05 최초 작성
 *===============================================================================*/
package com.roeuihyun.withfuture.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class XssFilterResponseConfig extends WebMvcConfigurationSupport{
	private final ObjectMapper objectMapper;

	@Override
	protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		
		super.configureMessageConverters(converters);
		
		// 3. WebMvcConfigurerAdapter에 MessageConverter 추가
		converters.add(jsonEscapeConverter());
		
	}
	
	@Bean
	public MappingJackson2HttpMessageConverter jsonEscapeConverter() {
		
		ObjectMapper copy = objectMapper.copy();
        copy.getFactory().setCharacterEscapes(new HtmlCharacterEscapes());
        return new MappingJackson2HttpMessageConverter(copy);
		
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/swagger-ui/**").addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
		super.addResourceHandlers(registry);
		
	}
	
	

	// Cross Domain 설정
	@Override
	protected void addCorsMappings(CorsRegistry registry) {
		
		registry.addMapping("/**") // CORS를 적용할 URL 패턴
				.allowedOrigins("*") // 자원 공유를 허락할 Origin
				.allowedMethods("GET", "POST", "PUT", "DELETE"); // 허용할 HTTP method
	}

	
}
