package com.example.demo.dataTransferObjects;

import java.util.Date;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class InvoiceDto {
	
	private int InvoiceId;
    private Date InvoiceDate;

   @Min(value = 3000, message = "Amount should be more than 3000")
   private double  amount;

   @NotBlank(message = "client name is requried")
   private String clientName;

   private String description;

   @NotNull(message = "User ID is required")
   private int userId;

}
