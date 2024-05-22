package com.example.demo.ExceptionHandling;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExceptionResponse {
	
	private int errorCode;
    private String message;
    private LocalDateTime dateTime;

}
