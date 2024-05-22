package com.example.demo.ExceptionHandling;

public class InvoiceNotFound extends RuntimeException{
	
	public InvoiceNotFound(int id) {
        super("Invoice with ID: " + id + " not found");
    }

}
