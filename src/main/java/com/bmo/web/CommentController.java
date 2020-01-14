package com.bmo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.bmo.po.Comment;
import com.bmo.service.CommentService;
import com.bmo.service.PostService;

@Controller
public class CommentController {

	@Autowired 
	CommentService commentService;
	
	@Autowired
	PostService postService;
	
	@PostMapping("/comments")
	public String post(Comment comment) {
		Long postId = comment.getPost().getId();
		comment.setPost(postService.getPost(postId));
		commentService.saveComment(comment);
		return "redirect:/posts/"+postId;
	}
}
