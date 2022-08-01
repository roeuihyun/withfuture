/*=================================================================================
 *                        Copyright(c) 2022 WithFuture
 *
 * Project                : withfuture
 * Source File Name       : com.roeuihyun.withfuture.configuration.SwaggerConfiguration
 * Description            :
 * Author                 : 노의현
 * Version                : 1.0.0
 * Created Date           : 2022.08.01
 * Updated Date           : 2022.08.01
 * Last Modifier          : 노의현
 * Updated Contents       : 
 * 2022.08.01 최초 작성
 *===============================================================================*/
package com.roeuihyun.withfuture.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration{
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.roeuihyun.withfuture.rest"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo());
	}
	
    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("withfuture API")
                .version("1.0.0")
                .description("API for basic CRUD for withfuture API")
                .contact(new Contact("WithFuture", "http://www.withfuture.com/", "roeuihyun@withfuture.com"))
                .license("WithFuture License Version 1.0")
                .build();
    }
    
}
