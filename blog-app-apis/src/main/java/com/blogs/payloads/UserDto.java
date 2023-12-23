package com.blogs.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty
	@Size(min = 4, message = "Username must be at least 4 charactors")
	private String name;

	@Email(message = "Email address is not valid !!")
	private String email;

	@NotEmpty
	@Size(min = 3, max = 10, message = "Password must be min of 3 chars and max of 10 chars !!")
	private String password;
	@NotEmpty
	private String about;

	private Set<RoleDto> roles = new HashSet<>();

}
