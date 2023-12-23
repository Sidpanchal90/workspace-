package com.blogs;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blogs.config.AppConstants;
import com.blogs.dau.RoleRepo;
import com.blogs.entities.Role;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {

		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println(this.passwordEncoder.encode("v@123"));

		try {
			Role role = new Role();
			role.setId(AppConstants.ADMIM_USER);
			role.setName("ROLE_ADMIN");

			Role role1 = new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("ROLE_NORMAL");

			List<Role> roles = List.of(role, role1);
			List<Role> result = this.roleRepo.saveAll(roles);

			result.forEach(r -> {
				System.out.println(r.getName());
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
