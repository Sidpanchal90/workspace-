package com.blogs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogs.payloads.ApiResponse;
import com.blogs.payloads.CommentDto;
import com.blogs.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/post/{postId}")
	public ResponseEntity<CommentDto> createComments(@RequestBody CommentDto commentDto, @PathVariable Integer postId) {

		CommentDto createComment = this.commentService.CreateComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(createComment, HttpStatus.CREATED);
	}

	@GetMapping("/getAllComments")
	public ResponseEntity<List<CommentDto>> getAllComments() {
		List<CommentDto> allComment = this.commentService.getAllComment();
		return new ResponseEntity<List<CommentDto>>(allComment, HttpStatus.OK);

	}

	@DeleteMapping("/{commentId}")
	public ResponseEntity<ApiResponse> deleteComments(@PathVariable Integer commentId) {
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment successfully deleted", true), HttpStatus.OK);

	}

}
