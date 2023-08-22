package com.example.todo.app.dao;

import com.example.todo.app.controller.User;

public interface UserDao {
	
	User getUserByName(String userName); 
	
	int createNewUser(User user);
}

