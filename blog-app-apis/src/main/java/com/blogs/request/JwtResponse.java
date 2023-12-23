package com.blogs.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {
	private String username;
	private String jwtToken;

}
