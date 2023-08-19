package com.pmp.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmp.ReqRes.Request;
import com.pmp.ReqRes.Response;
import com.pmp.entity.UserProfileData;
import com.pmp.exception.DisabledUserException;
import com.pmp.exception.InvalidUserCredentialsException;
import com.pmp.service.UserAuthService;
import com.pmp.util.JwtUtil;

@RestController
@CrossOrigin(value = "http://localhost:4200")
public class JwtRestApi {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserAuthService authService;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private UserAuthService userAuthService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/signin")
	public ResponseEntity<Response> generateJwtToken(@RequestHeader("userName") String userName,
			@RequestHeader("userPwd") String userPass) {

		// public ResponseEntity<Response> generateJwtToken(@RequestBody Request
		// request) {
		Authentication authentication = null;
		try {
			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(userName, userPass));
		} catch (DisabledException e) {
			throw new DisabledUserException("User Inactive");
		} catch (BadCredentialsException e) {
			throw new InvalidUserCredentialsException("Invalid Credentials");
		}

		User user = (User) authentication.getPrincipal();
		Set<String> roles = user.getAuthorities().stream().map(r -> r.getAuthority()).collect(Collectors.toSet());

		String token = jwtUtil.generateToken(authentication);

		Response response = new Response();
		response.setToken(token);
		//response.setRoles(roles.stream().collect(Collectors.toList()));
		return (new ResponseEntity<Response>(response, HttpStatus.OK));

	}

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody Request request) {
		userAuthService.saveUser(request);

		return new ResponseEntity<String>("User successfully registered", HttpStatus.OK);
	}

	/*
	 * @ResponseStatus(value = HttpStatus.OK)
	 * 
	 * @PostMapping("/upload") public String uploadImage(@RequestParam("file")
	 * MultipartFile file) throws IOException { userAuthService.uploadImage(file);
	 * return "Image successfully uploade"; }
	 */

	@PostMapping("/saveUserProfile")
	public ResponseEntity<String> saveUserProfile(@RequestParam("file") MultipartFile file,
			@RequestParam("userData") String userData) throws IOException {

		Request request = mapper.readValue(userData, Request.class);

		userAuthService.saveUserProfile(file, request);

		return new ResponseEntity<String>("User successfully registered", HttpStatus.OK);

	}

	@GetMapping("/getUserProfileById/{id}")
	public Optional<UserProfileData> getUserProfileById(@PathVariable int id) {
		Optional<UserProfileData> userProfileById = userAuthService.getUserProfileById(id);
		return userProfileById;

	}

	// @PreAuthorize("hasRole('USER')")
	@GetMapping("/userProfile")
	public List<UserProfileData> getUserProfile() {
		List<UserProfileData> findAll = authService.getUserProfile();
		return findAll;
	}

	@GetMapping("/greet/user")
	public ResponseEntity<String> greetingUser() {
		return new ResponseEntity<String>("Welcome, you have ADMIN role", HttpStatus.OK);
	}

	// @PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/greet/admin")
	public ResponseEntity<String> greetingAdmin() {
		return new ResponseEntity<String>("Welcome, you have ADMIN role", HttpStatus.OK);
	}

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/greet/userOrAdmin")
	public ResponseEntity<String> greetingUserOrAdmin() {
		return new ResponseEntity<String>("Welcome, you have USER and ADMIN role", HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ANONYMOUS')")
	@GetMapping("/greet/anonymous")
	public ResponseEntity<String> greetingAnonymous() {
		return new ResponseEntity<String>("Welcome, you have ANONYMOUS role", HttpStatus.OK);
	}

}
