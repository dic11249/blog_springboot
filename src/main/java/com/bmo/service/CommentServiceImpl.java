package com.bmo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bmo.dao.CommentRepository;
import com.bmo.po.Comment;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	CommentRepository commentRepository;
	
	@Override
	public List<Comment> listByPostId(Long postId) {
		Sort sort = new Sort(Sort.Direction.ASC, "createTime");
		return commentRepository.findByPostId(postId, sort);
	}
	
	@Transactional
	@Override
	public Comment saveComment(Comment comment) {
		Long parentCommentId = comment.getParentComment().getId();
		if(parentCommentId != -1) {
			comment.setParentComment(commentRepository.findById(parentCommentId).orElse(null));			
		} else {
			comment.setParentComment(null);
		}
		comment.setCreateTime(new Date());
		return commentRepository.save(comment);
	}

	

}
