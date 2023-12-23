package com.blogs.dau;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogs.entities.Comments;
import com.blogs.payloads.CommentDto;

@Repository
public interface CommentRepo extends JpaRepository<Comments, Integer> {

	void save(CommentDto commentDto);

}
