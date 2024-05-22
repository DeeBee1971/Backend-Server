package com.example.demo.packageEntities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User{
	
	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name = "user_id")
	private int userId;
	
	@Column(unique = true, name = "username")
    @NotBlank(message = "Enter valid username")
	private String userName ;
	
	@NotBlank(message = "Email address is required")
	@Column(unique = true, name = "email")
    @Email(message = "Invalid email")
	private String email;
	
	@NotEmpty(message = "password should be not empty")
	@Column(name = "password")
	private String password;
	
	
	@Column(name = "date_of_creation")
    @CreationTimestamp
	private Date dateOfRegistration;
	
}
