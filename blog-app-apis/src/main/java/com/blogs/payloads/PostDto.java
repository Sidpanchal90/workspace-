package com.blogs.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PostDto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer postId;
	@NotBlank

	@Size(min = 10, message = "Minimum size of title is 10")
	private String title;

	@NotBlank

	@Size(min = 20, message = "Minimum size of content is 20")
	private String content;

	@NotBlank
	private String imageName;

	@NotBlank
	private Date addedDate;

	@NotBlank
	private CategoryDto category;

	@NotBlank
	private UserDto user;

	@NotBlank
	private Set<CommentDto> comments = new HashSet<>();
	// private List<CommentDto> comments = new ArrayList<>();

}
