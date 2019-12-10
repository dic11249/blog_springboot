package com.bmo.service;

import com.bmo.po.User;

public interface UserService {

	User checkUser(String username, String password);
}
