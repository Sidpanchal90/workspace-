package com.blogs.services;

import java.util.List;

import com.blogs.payloads.CommentDto;

public interface CommentService {

	CommentDto CreateComment(CommentDto commentDto, Integer postId);

	void deleteComment(Integer commentId);

	List<CommentDto> getAllComment();

}
