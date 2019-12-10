package com.bmo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmo.po.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsernameAndPassword(String username, String password);
}
