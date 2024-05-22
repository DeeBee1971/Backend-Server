package com.example.demo.packageEntities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice , Integer>{

	List<Invoice> findByUserUserId(Integer userId);
}
