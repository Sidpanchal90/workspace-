package com.blogs.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogs.dau.CommentRepo;
import com.blogs.dau.PostRepo;
import com.blogs.entities.Comments;
import com.blogs.entities.Post;
import com.blogs.exception.ResourceNotFoundException;
import com.blogs.payloads.CommentDto;

@Service
public class CommentServiceImpl implements com.blogs.services.CommentService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public CommentDto CreateComment(CommentDto commentDto, Integer postId) {

		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

		Comments comments = modelMapper.map(commentDto, Comments.class);

		comments.setPost(post);

		Comments savedComment = this.commentRepo.save(comments);

		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {

		Comments deleteComment = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
		this.commentRepo.delete(deleteComment);
	}

	@Override
	public List<CommentDto> getAllComment() {

		List<Comments> findAll = this.commentRepo.findAll();
		List<CommentDto> commentDto = findAll.stream().map(commet -> this.modelMapper.map(commet, CommentDto.class))
				.collect(Collectors.toList());
		return commentDto;
	}

}
