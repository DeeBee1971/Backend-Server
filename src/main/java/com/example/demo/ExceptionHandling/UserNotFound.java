package com.example.demo.ExceptionHandling;

public class UserNotFound extends RuntimeException{
	
	public UserNotFound(int id) {
        super("User  with ID: " + id + " not found");
    }

}
