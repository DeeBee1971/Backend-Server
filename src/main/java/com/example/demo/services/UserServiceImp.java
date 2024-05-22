package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dataTransferObjects.userDto;
import com.example.demo.packageEntities.User;
import com.example.demo.packageEntities.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class UserServiceImp implements UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImp.class);
    @Autowired
    private UserRepository userRepository;

    public int getUser(String username, String password) {
        User user = userRepository.findByUserName(username);
        if (user != null) {
            if (user.getPassword().equals(password)) return user.getUserId();
            else return -2; 
        }
        else return -1;  
        }

    
    public int createUser( userDto user) {
        log.info("create user called");

        if (userRepository.findByEmail(user.getEmail()) != null) {
            return 2;
        }
        if (userRepository.findByUserName(user.getUserName()) != null) {
            return 3;
        }
        User userNew = User.builder()
                .userName(user.getUserName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
        userRepository.save(userNew);
        return 1; 
    }

}
