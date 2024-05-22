package com.example.demo.packageEntities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor 
public class Invoice {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")  
    private int invoiceId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")  
    private User user;

    @NotBlank(message = "client name is required")
    @Column(name = "client_name")  
    private String clientName;

    @Min(value = 3000, message = "Amount should be more than 3000")
    @Column(name = "amount")  
    private double amount;

    @Column(name = "invoice_date")  
    private Date invoiceDate;

    @Column(name = "description")  
    private String description;
}
