package com.example.demo.packageEntities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User , Integer> {
	User findByUserName(String username);
    User findByEmail(String email);
}
