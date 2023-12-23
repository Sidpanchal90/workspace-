package com.blogs.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swaggercongif {

	public static final String AUTHORIZATION_HEADER = "Authorization";

	private ApiKey apiKeys() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}

	private List<SecurityContext> securityContexts() {
		return Arrays.asList(SecurityContext.builder().securityReferences(securityRef()).build());
	}

	private List<SecurityReference> securityRef() {

		AuthorizationScope scope = new AuthorizationScope("global", "accessEverything");

		return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] { scope }));
	}

	@Bean
	Docket api() {

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getInfo()).securityContexts(securityContexts())
				.securitySchemes(java.util.Arrays.asList(apiKeys())).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();

	}

	private ApiInfo getInfo() {
		return new ApiInfo("Blogging Applications : Backend Course", "This project developed by PMI", "1.0",
				"Termes of Services", new Contact("Sidheshwar", "", "panchalsidheshwar90@gmail.com"), "License of APIs",
				"API license URL", Collections.emptyList());
	}

}
