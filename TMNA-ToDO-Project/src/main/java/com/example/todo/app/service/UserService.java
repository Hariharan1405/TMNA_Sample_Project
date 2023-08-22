package com.example.todo.app.service;

import com.example.todo.app.controller.User;

public interface UserService {
	
	User getUserByUserName(String userName);
	
	int createNewUser(User user);
}
