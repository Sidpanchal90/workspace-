package com.employee.srvices.HomeController;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.srvices.Model.User;
import com.employee.srvices.UserService.userService;

@RestController
@RequestMapping("/home")
public class HomeController {

	@Autowired 
	private userService userservice;
	
	@GetMapping("/user")
	public List<User> getUser() {
		System.out.println("userinfo");
		return this.userservice.getUser();
	}
//This interface represents the abstract notion of a principal, which can be used to represent any entity, such as an individual, acorporation, 
	//and a login id.  it returns the current login user 
	@GetMapping("/CurrentUser")
	public String getLoogInUser(Principal principle) {
		return principle.getName();
		
	}
}
