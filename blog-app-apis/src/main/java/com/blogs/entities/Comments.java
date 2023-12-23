package com.blogs.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "comments")
public class Comments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String content;

	@ManyToOne
	@JoinColumn(name = "postId")
	private Post post;

}
