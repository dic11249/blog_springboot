package com.bmo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bmo.po.Post;
import com.bmo.vo.PostQuery;

/**
 * @author Bmo  2019-12-15  
 *
 */
public interface PostService {

	Post getPost(Long id);
	
	Page<Post> listPost(Pageable pageable, PostQuery post);
	
	Page<Post> listPost(Pageable pageable);
	
	List<Post> listRecommendPostTop(Integer size);
	
	Post savePost(Post post);
	
	Post updatePost(Long id, Post post);
	
	void deletePost(Long id);
}
