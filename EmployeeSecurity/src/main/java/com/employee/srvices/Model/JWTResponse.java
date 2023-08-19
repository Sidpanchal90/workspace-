package com.employee.srvices.Model;

public class JWTResponse {

	private String jwtToken;
	private String username;

	public JWTResponse(String token, String username2) {
		// TODO Auto-generated constructor stub
	}

	public String getJwtToken() {
		return jwtToken;
	}

	@Override
	public String toString() {
		return "JWTResponse [jwtToken=" + jwtToken + ", username=" + username + "]";
	}

	public JWTResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
