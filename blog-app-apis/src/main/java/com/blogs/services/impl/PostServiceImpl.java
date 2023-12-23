package com.blogs.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blogs.dau.CategoryRepo;
import com.blogs.dau.PostRepo;
import com.blogs.dau.UserRepo;
import com.blogs.entities.Category;
import com.blogs.entities.Post;
import com.blogs.entities.User;
import com.blogs.exception.ResourceNotFoundException;
import com.blogs.payloads.PostDto;
import com.blogs.payloads.PostResponse;
import com.blogs.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post newPost = this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {

		Post posts = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		posts.setContent(postDto.getContent());
		posts.setTitle(postDto.getTitle());
		posts.setImageName(postDto.getImageName());

		Post updatedPost = postRepo.save(posts);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {

		Post posts = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		this.postRepo.delete(posts);

	}

	@Override
	public List<PostDto> getAllPost() {
		List<Post> allPost = this.postRepo.findAll();

		List<PostDto> postDtos = allPost.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;

	}

	@Override
	public PostResponse getAllPostByPagenation(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

		// using ternory operator
		Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		/*
		 * //using if else (sortDir.equalsIgnoreCase("asc")) { sort =
		 * Sort.by(sortBy).ascending();
		 * 
		 * } else { sort = Sort.by(sortBy).descending(); }
		 */

		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = this.postRepo.findAll(p);
		List<Post> allPost = pagePost.getContent();// get list post

		List<PostDto> postDtos = allPost.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());

		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post postById = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

		return this.modelMapper.map(postById, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {

		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		List<Post> posts = this.postRepo.findByCategory(cat);

		List<PostDto> postDtos = posts.stream().map(c -> this.modelMapper.map(c, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
		List<Post> posts = this.postRepo.findByUser(user);

		List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;
	}

	public List<PostDto> searchPosts(String keywords) {
		List<Post> posts = this.postRepo.searchByTitle("%" + keywords + "%");
		List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;
	}

}
