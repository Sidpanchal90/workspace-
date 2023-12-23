package com.blogs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogs.dau.PostRepo;
import com.blogs.dau.UserRepo;
import com.blogs.payloads.ApiResponse;
import com.blogs.payloads.CategoryDto;
import com.blogs.services.CategoryService;

@RestController
@RequestMapping("api/")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserRepo userRepo;

	@PostMapping("/categories")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {

		CategoryDto categorySave = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(categorySave, HttpStatus.CREATED);

	}

	@PutMapping("/categories/{categoryId}")
	public ResponseEntity<CategoryDto> updatePostById(@RequestBody CategoryDto categoryDto,
			@PathVariable Integer categoryId) {
		CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, categoryId);

		return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);

	}

	@GetMapping("/categories")
	public ResponseEntity<List<CategoryDto>> getAllPost() {
		List<CategoryDto> allCategory = this.categoryService.getAllCategory();
		return new ResponseEntity<List<CategoryDto>>(allCategory, HttpStatus.OK);
	}

	@GetMapping("/categories/{categoryId}")
	public ResponseEntity<CategoryDto> getPostsByUser(@PathVariable Integer categoryId) {
		// Optional<User> findById = this.userRepo.findById(userId);
		CategoryDto singleCategory = this.categoryService.getCategory(categoryId);
		return new ResponseEntity<CategoryDto>(singleCategory, HttpStatus.OK);
	}

	@DeleteMapping("/categories/{categoryId}")
	public ResponseEntity<ApiResponse> deletePostById(@PathVariable Integer categoryId) {

		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category successfully deleted", true), HttpStatus.OK);

	}
	
	

}
