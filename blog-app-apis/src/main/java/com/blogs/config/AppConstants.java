package com.blogs.config;

public class AppConstants {

	public static final String PAGE_NUMBR = "0";
	public static final String PAGE_SIZE = "10";
	public static final String SORT_BY = "postId";
	public static final String SORT_DIR = "asc";
	public static final Integer NORMAL_USER = 502;
	public static final Integer ADMIM_USER = 501;
	public static final String[] PUBLIC_URLS = { "/api/auth/**", 
			"/v3/api-docs",
			"/v2/api-docs",
			"/swagger-resources/**",
			"/swagger-ui/**",
			"/webjars/**"
			
			
			};

}
