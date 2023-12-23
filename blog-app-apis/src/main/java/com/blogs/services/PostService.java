package com.blogs.services;

import java.util.List;

import com.blogs.payloads.PostDto;
import com.blogs.payloads.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	PostDto updatePost(PostDto postDto, Integer postId);

	void deletePost(Integer postId);

	List<PostDto> getAllPost();

	PostResponse getAllPostByPagenation(Integer pageNumber, Integer pageSize, String sortBy,String sortDir);

	PostDto getPostById(Integer postId);

	List<PostDto> getPostsByCategory(Integer categoryId);

	List<PostDto> getPostsByUser(Integer userId);

	List<PostDto> searchPosts(String keyword);

}
