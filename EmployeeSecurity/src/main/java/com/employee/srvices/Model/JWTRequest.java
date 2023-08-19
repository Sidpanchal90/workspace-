package com.employee.srvices.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


public class JWTRequest {

	@JsonProperty("email")
	private String email;
	@JsonProperty("password")
	private String password;
	public JWTRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public JWTRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "JWTRequest [email=" + email + ", password=" + password + "]";
	}
	
}
