package com.employee.srvices.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.employee.srvices.Model.User;


@Service
public class userService {

	private List<User>list= new ArrayList<>();

	public userService() {
		//list.add(new User(UUID.randomUUID().toString(),"Shivkumar","shivswami538@gmail.com"));
	}
	public List<User>getUser(){
		return this.list;
	}
}
