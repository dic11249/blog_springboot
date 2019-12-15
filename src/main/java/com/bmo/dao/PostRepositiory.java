package com.bmo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bmo.po.Post;

/**
 * @author Bmo  2019-12-15  
 *
 */
public interface PostRepositiory extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post>{

}
