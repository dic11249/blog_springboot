package com.bmo.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.bmo.po.Post;

/**
 * @author Bmo  2019-12-15  
 *
 */
public interface PostRepositiory extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post>{

	@Query("select p from Post p where p.recommend = true")
	List<Post> findTop(Pageable pageable);
}
