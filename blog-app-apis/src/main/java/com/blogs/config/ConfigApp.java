package com.blogs.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConfigApp {
	@Bean
	public ModelMapper modelmapper() {
		return new ModelMapper();
	}

	
	/*
	 * @Bean public AuthenticationManager
	 * authenticationManager(AuthenticationConfiguration builder) throws Exception {
	 * return builder.getAuthenticationManager(); }
	 */

}
