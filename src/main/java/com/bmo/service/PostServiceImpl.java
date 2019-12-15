package com.bmo.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.bmo.NotFoundException;
import com.bmo.dao.PostRepositiory;
import com.bmo.po.Post;
import com.bmo.po.Type;

/**
 * @author Bmo  2019-12-15  
 *
 */
@Service
public class PostServiceImpl implements PostService{

	@Autowired PostRepositiory postRepositiory;
	
	@Override
	public Post getPost(Long id) {
		return postRepositiory.findById(id).orElse(null);
	}

	@Override
	public Page<Post> listPost(Pageable pageable, Post post) {
		return postRepositiory.findAll(new Specification<Post>() {
			
			@Override
			public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if(!"".equals(post.getTitle()) && post.getTitle() != null) {
					predicates.add(cb.like(root.<String>get("title"), "%"+post.getTitle()+"%"));
				}
				if(post.getType().getId() != null) {
					predicates.add(cb.equal(root.<Type>get("type").get("id"), post.getType().getId()));
				}
				if(post.isCommentabled()) {
					predicates.add(cb.equal(root.<Boolean>get("recommend"), post.isCommentabled()));
				}
				query.where(predicates.toArray(new Predicate[predicates.size()]));
				return null;
			}
		}, pageable);
	}

	@Override
	public Post savePost(Post post) {
		return postRepositiory.save(post);
	}

	@Override
	public Post updatePost(Long id, Post post) {
		Post p = postRepositiory.findById(id).orElse(null);
		if (p == null) {
			throw new NotFoundException("該文章不存在");
		}
		BeanUtils.copyProperties(post, p);
		return postRepositiory.save(p);
	}

	@Override
	public void deletePost(Long id) {
		postRepositiory.deleteById(id);
	}

}
