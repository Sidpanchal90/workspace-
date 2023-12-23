package com.blogs.request;

import lombok.Data;

@Data
public class JwtRequest {
	private String password;
	private String email;

}
