package com.bmo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bmo.po.Post;

/**
 * @author Bmo  2019-12-15  
 *
 */
public interface PostService {

	Post getPost(Long id);
	
	Page<Post> listPost(Pageable pageable, Post post);
	
	Post savePost(Post post);
	
	Post updatePost(Long id, Post post);
	
	void deletePost(Long id);
}
