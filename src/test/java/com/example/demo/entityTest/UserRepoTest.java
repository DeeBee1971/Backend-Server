package com.example.demo.entityTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;

import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.demo.packageEntities.User;
import com.example.demo.packageEntities.UserRepository;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
public class UserRepoTest {
	
	@Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveUserTest() {
        User user = new User.UserBuilder()
                .userName("newuser")
                .email("new@email.com")
                .password("password")
                .dateOfRegistration(new Date())
                .build();

                userRepository.save(user);
        assertThat(user.getUserId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getUserByNameTest() {
        User user = userRepository.findByUserName("newuser");
        assertThat(user.getUserName()).isEqualTo("newuser");

    }


    @Test
    @Order(3)
    public void getUserByEmailTest() {
        User user = userRepository.findByEmail("new@email.com");
        assertThat(user.getEmail()).isEqualTo("new@email.com");

    }
    
    @Test
    @Order(4)
    public void getUserByNonExistentNameTest() {
        User user = userRepository.findByUserName("nonexistentuser");
        assertNull(user);
    }
    @Test
    @Order(5)
    public void getUserByNonExistentEmailTest() {
        User user = userRepository.findByEmail("nonexistent@email.com");
        assertNull(user);
    }

}
