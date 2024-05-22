package com.example.demo.dataTransferObjects;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class userDto {
	
	@NotBlank(message = "Username is required")
    private String userName;
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;

}
