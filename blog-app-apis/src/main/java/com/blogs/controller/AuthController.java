
package com.blogs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogs.exception.APIException;
import com.blogs.payloads.JWTAuthResponse;
import com.blogs.payloads.JwtAuthRequest;
import com.blogs.payloads.UserDto;
import com.blogs.security.JwtTokenHelper;
import com.blogs.services.UserService;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<JWTAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {

		this.authenticate(request.getUsername(), request.getPassword());
		UserDetails userDeatil = this.userDetailsService.loadUserByUsername(request.getUsername());

		String token = this.jwtTokenHelper.generateToken(userDeatil);

		JWTAuthResponse response = new JWTAuthResponse();
		response.setToken(token);

		return new ResponseEntity<JWTAuthResponse>(response, HttpStatus.OK);

	}

	private void authenticate(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);

		try {
			this.authenticationManager.authenticate(authenticationToken);

		} catch (BadCredentialsException e) {
			System.out.println("Invalid details..!!");
			throw new APIException("Invalid Username and Password..!!");
		}
	}

	@PostMapping("/register")
	public ResponseEntity<UserDto> registerNewUser(@RequestBody UserDto userDto) {
		UserDto registerNewUser = this.userService.registerNewUser(userDto);
		return new ResponseEntity<UserDto>(registerNewUser, HttpStatus.CREATED);
	}

}
