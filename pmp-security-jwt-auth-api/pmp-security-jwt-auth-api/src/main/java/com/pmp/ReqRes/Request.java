package com.pmp.ReqRes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Lob;

public class Request {

	private int id;
	private String userName;
	private String email;
	private String userPwd;
	private String name;
	private String type;
	private List<String> roles;

	@Lob
	@Column(name = "imagedata")
	private byte[] imageData;

	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Request(int id, String userName, String email, String userPwd, String name, String type, List<String> roles,
			byte[] imageData) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.userPwd = userPwd;
		this.name = name;
		this.type = type;
		this.roles = roles;
		this.imageData = imageData;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
