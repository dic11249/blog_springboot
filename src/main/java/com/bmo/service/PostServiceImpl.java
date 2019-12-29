package com.bmo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bmo.NotFoundException;
import com.bmo.dao.PostRepositiory;
import com.bmo.po.Post;
import com.bmo.po.Type;
import com.bmo.util.MyBeanUtils;
import com.bmo.vo.PostQuery;

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
	public Page<Post> listPost(Pageable pageable, PostQuery post) {
		return postRepositiory.findAll(new Specification<Post>() {
			
			@Override
			public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if(!"".equals(post.getTitle()) && post.getTitle() != null) {
					predicates.add(cb.like(root.<String>get("title"), "%"+post.getTitle()+"%"));
				}
				if(post.getTypeId() != null) {
					predicates.add(cb.equal(root.<Type>get("type").get("id"), post.getTypeId()));
				}
				if(post.isRecommend()) {
					predicates.add(cb.equal(root.<Boolean>get("recommend"), post.isRecommend()));
				}
				query.where(predicates.toArray(new Predicate[predicates.size()]));
				return null;
			}
		}, pageable);
	}
	
	@Override
	public Page<Post> listPost(Pageable pageable) {
		return postRepositiory.findAll(pageable);
	}
	
	@Override
	public List<Post> listRecommendPostTop(Integer size) {
		Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
		Pageable pageable =  PageRequest.of(0, size, sort);
		return postRepositiory.findTop(pageable);
	}

	@Transactional
	@Override
	public Post savePost(Post post) {
		if(post.getId()==null) {
			post.setCreateTime(new Date());
			post.setUpdateTime(new Date());
			post.setViews(0);
		} else {
			post.setUpdateTime(new Date());
		}
		
		return postRepositiory.save(post);
	}

	@Transactional
	@Override
	public Post updatePost(Long id, Post post) {
		Post p = postRepositiory.findById(id).orElse(null);
		if (p == null) {
			throw new NotFoundException("該文章不存在");
		}
		BeanUtils.copyProperties(post, p, MyBeanUtils.getNullPropertyNames(post));
		p.setUpdateTime(new Date());
		return postRepositiory.save(p);
	}

	@Transactional
	@Override
	public void deletePost(Long id) {
		postRepositiory.deleteById(id);
	}




}
