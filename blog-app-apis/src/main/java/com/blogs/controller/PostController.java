package com.blogs.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blogs.config.AppConstants;
import com.blogs.config.FileService;
import com.blogs.payloads.ApiResponse;
import com.blogs.payloads.PostDto;
import com.blogs.payloads.PostResponse;
import com.blogs.services.PostService;

@RestController
@RequestMapping("api/")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {

		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);

	}

	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto, @PathVariable Integer postId) {
		PostDto updatePost = this.postService.updatePost(postDto, postId);

		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);

	}

	@GetMapping("/getAllPost")
	public ResponseEntity<List<PostDto>> getAllPost() {
		List<PostDto> allPost = this.postService.getAllPost();
		return new ResponseEntity<List<PostDto>>(allPost, HttpStatus.OK);
	}

	@GetMapping("/getAllPostByPagenation")
	public ResponseEntity<PostResponse> getAllPostByPagenation(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBR, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {

		PostResponse postResponse = this.postService.getAllPostByPagenation(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
		// Optional<User> findById = this.userRepo.findById(userId);
		List<PostDto> posts = this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {
		// Optional<User> findById = this.userRepo.findById(userId);
		List<PostDto> postsByCategory = this.postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(postsByCategory, HttpStatus.OK);

	}

	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostsById(@PathVariable Integer postId) {
		// Optional<User> findById = this.userRepo.findById(userId);
		PostDto postById = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postById, HttpStatus.OK);

	}

	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePostById(@PathVariable Integer postId) {

		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User successfully deleted", true), HttpStatus.OK);

	}

	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keywords) {
		List<PostDto> searchPosts = this.postService.searchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(searchPosts, HttpStatus.OK);
	}

	@PostMapping("/posts/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@PathVariable Integer postId,
			@RequestParam("image") MultipartFile image) throws IOException {
		PostDto postDto = this.postService.getPostById(postId);
		String fileName = this.fileService.uploadImage(path, image);

		postDto.setImageName(fileName);
		PostDto updatePost = this.postService.updatePost(postDto, postId);

		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);

	}

	@GetMapping(value = "/posts/image/{imageName}", produces = org.springframework.http.MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		org.springframework.util.StreamUtils.copy(resource, response.getOutputStream());
	}

}
