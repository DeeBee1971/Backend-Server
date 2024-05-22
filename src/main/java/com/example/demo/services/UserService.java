package com.example.demo.services;

import com.example.demo.ExceptionHandling.UserNotFound;
import com.example.demo.dataTransferObjects.userDto;

public interface UserService {
	
	int createUser(userDto userDto);

    int getUser(String username, String password) throws UserNotFound;

}
