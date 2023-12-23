package com.blogs.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Role {

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)(We comments that because we
	// assign role directly for register new user )
	private int id;

	private String name;

}
