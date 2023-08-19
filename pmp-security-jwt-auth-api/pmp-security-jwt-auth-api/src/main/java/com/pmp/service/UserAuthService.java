package com.pmp.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.pmp.ReqRes.Request;
import com.pmp.entity.User;
import com.pmp.entity.UserRole;
import com.pmp.repository.UserProfileData;
import com.pmp.repository.UserRepository;
import com.pmp.util.ImageUtil;

@Service
public class UserAuthService implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserProfileData userProfileData;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username).get();

		List<UserRole> userRoles = user.getUserRoles().stream().collect(Collectors.toList());

		List<GrantedAuthority> grantedAuthorities = userRoles.stream().map(r -> {
			return new SimpleGrantedAuthority(r.getRole());
		}).collect(Collectors.toList());

		return new org.springframework.security.core.userdetails.User(username, user.getUserPass(), grantedAuthorities);
	}

	public void saveUser(Request request) {
		if (userRepository.findByUserName(request.getUserName()).isPresent()) {
			throw new RuntimeException("User already exists");
		}

		User user = new User();
		user.setUserName(request.getUserName());
		user.setEmail(request.getEmail());
		user.setUserPass(passwordEncoder.encode(request.getUserPwd()));

		user.setUserRoles(request.getRoles().stream().map(r -> {
			UserRole ur = new UserRole();
			ur.setRole(r);
			return ur;
		}).collect(Collectors.toSet()));

		userRepository.save(user);
	}

	/*
	 * // upload image public String uploadImage(MultipartFile file) throws
	 * IOException { com.pmp.entity.UserProfileData pImage = new
	 * com.pmp.entity.UserProfileData(); pImage.setName(file.getOriginalFilename());
	 * pImage.setType(file.getContentType());
	 * pImage.setImageData(ImageUtil.compressImage(file.getBytes()));
	 * userProfileData.save(pImage); return null;
	 * 
	 * }
	 */

	public Optional<com.pmp.entity.UserProfileData> getUserProfileById(int id) {
		Optional<com.pmp.entity.UserProfileData> getuserProfileData = userProfileData.findById(id);
		return getuserProfileData;
	}

	public void saveUserProfile(MultipartFile file, Request readValue) throws IOException {

		if (userProfileData.findByUserName(readValue.getUserName()).isPresent()) {
			throw new RuntimeException("User already exists");

		}

		com.pmp.entity.UserProfileData user = new com.pmp.entity.UserProfileData();
		user.setUserName(readValue.getUserName());
		user.setEmail(readValue.getEmail());
		user.setUserPwd(passwordEncoder.encode(readValue.getUserPwd()));

		user.setImageData(ImageUtil.compressImage(file.getBytes()));

		user.setName(file.getOriginalFilename());
		user.setType(file.getContentType());

		userProfileData.save(user);
	}

	public List<com.pmp.entity.UserProfileData> getUserProfile() {
		List<com.pmp.entity.UserProfileData> findAll = userProfileData.findAll();
		return findAll;

	}

}
