package com.example.todo.app.service.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.app.controller.User;
import com.example.todo.app.dao.UserDao;
import com.example.todo.app.service.UserService;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public User getUserByUserName(String userName) {
		User user = userDao.getUserByName(userName);
		return user;
	}

	@Override
	public int createNewUser(User user) {
		return userDao.createNewUser(user);
	}
	
}
