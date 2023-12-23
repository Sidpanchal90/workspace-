package com.blogs.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogs.config.AppConstants;
import com.blogs.dau.RoleRepo;
import com.blogs.dau.UserRepo;
import com.blogs.entities.Role;
import com.blogs.entities.User;
import com.blogs.exception.ResourceNotFoundException;
import com.blogs.payloads.UserDto;
import com.blogs.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {

		User user = this.dtoToUser(userDto);

		User savedUser = userRepo.save(user);

		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		User updateUser = userRepo.save(user);

		UserDto userToDto1 = this.userToDto(updateUser);
		return userToDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userRepo.findAll();

		List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);

	}

	public User dtoToUser(UserDto userDto) {

		User user = modelMapper.map(userDto, User.class);
		/*
		 * User user = new User(); user.setId(userDto.getId());
		 * user.setName(userDto.getName()); user.setEmail(userDto.getEmail());
		 * user.setPassword(userDto.getPassword()); user.setAbout(userDto.getAbout());
		 */
		return user;

	}

	public UserDto userToDto(User user) {

		UserDto userDto = modelMapper.map(user, UserDto.class);
		/*
		 * UserDto userDto = new UserDto(); userDto.setId(user.getId());
		 * userDto.setName(user.getName()); userDto.setEmail(user.getEmail());
		 * userDto.setAbout(user.getAbout()); userDto.setPassword(user.getPassword());
		 */

		return userDto;
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		// encoded the password
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		// Roles
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);

		User newUser = this.userRepo.save(user);

		return this.modelMapper.map(newUser, UserDto.class);

	}

	/*
	 * @Override public UserDto registernewUser(UserDto userDto) { // set password
	 * User user = this.modelMapper.map(userDto, User.class);
	 * user.setPassword(passwordEncoder.encode(user.getPassword())); // set roles
	 * 
	 * Role role = roleRepo.findById(AppConstaint.NORMAL_USER).get();
	 * user.getRoles().add(role);
	 * 
	 * User roleUser = this.userRepo.save(user); return
	 * this.modelMapper.map(roleUser, UserDto.class); }
	 */
}
