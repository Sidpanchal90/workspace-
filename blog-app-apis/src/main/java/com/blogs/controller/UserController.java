package com.blogs.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogs.payloads.ApiResponse;
import com.blogs.payloads.UserDto;
import com.blogs.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	// Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);

	@Autowired
	public UserService userService;

	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
		UserDto createUser = this.userService.registerNewUser(userDto);

		return new ResponseEntity<UserDto>(createUser, HttpStatus.CREATED);
	}

	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createUser = this.userService.createUser(userDto);

		return new ResponseEntity<UserDto>(createUser, HttpStatus.CREATED);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<ApiResponse> updatUser(@Valid @RequestBody UserDto userDto,
			@PathVariable("userId") Integer uid) {
		userService.updateUser(userDto, uid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User successfully updated", true), HttpStatus.OK);

	}

	// Admin
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUserByid(@PathVariable("userId") Integer uid) {
		userService.deleteUser(uid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User successfully deleted", true), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser() {
		return ResponseEntity.ok(this.userService.getAllUsers());

	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer uid) {
		return ResponseEntity.ok(this.userService.getUserById(uid));

	}

}