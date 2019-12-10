package com.bmo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmo.dao.UserRepository;
import com.bmo.po.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired UserRepository userRepository;
	
	@Override
	public User checkUser(String username, String password) {
		User user = userRepository.findByUsernameAndPassword(username, password);
		return user;
	}

}
