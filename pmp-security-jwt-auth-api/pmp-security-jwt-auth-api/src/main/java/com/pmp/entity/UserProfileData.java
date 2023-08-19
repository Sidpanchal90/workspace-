package com.pmp.entity;

import java.util.Arrays;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table
public class UserProfileData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String userName;
	private String email;
	private String userPwd;
	private String name;
	private String type;
	/*
	 * @OneToMany(mappedBy = "userProfileData", cascade = CascadeType.ALL) private
	 * Set<UserRole> userRoles = new HashSet<>();
	 */
	@Lob
	@Column(name = "imagedata")
	private byte[] imageData;

	public UserProfileData() {
		super();
	}

	public UserProfileData(int id, String userName, String email, String userPwd, String name, String type,
			Set<UserRole> userRoles, byte[] imageData) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.userPwd = userPwd;
		this.name = name;
		this.type = type;
		// this.userRoles = userRoles;
		this.imageData = imageData;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	/*
	 * public Set<UserRole> getUserRoles() { return userRoles; }
	 * 
	 * public void setUserRoles(Set<UserRole> userRoles) { this.userRoles =
	 * userRoles; for (UserRole rt : userRoles) { rt.setUserProfileData(this);
	 * 
	 * } }
	 */

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

	public String setImageData(String originalFilename) {
		return originalFilename;

	}

	@Override
	public String toString() {
		return "UserProfileData [id=" + id + ", userName=" + userName + ", email=" + email + ", userPwd=" + userPwd
				+ ", name=" + name + ", type=" + type + ", userRoles=" + ", imageData=" + Arrays.toString(imageData)
				+ "]";
	}

}
