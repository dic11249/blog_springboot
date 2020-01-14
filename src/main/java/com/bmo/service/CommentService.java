package com.bmo.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.bmo.po.Comment;

public interface CommentService {

	List<Comment> listByPostId(Long postId);
	
	Comment saveComment(Comment comment);
}
