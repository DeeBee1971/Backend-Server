package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ExceptionHandling.InvoiceNotFound;
import com.example.demo.dataTransferObjects.InvoiceDto;
import com.example.demo.dataTransferObjects.userDto;
import com.example.demo.services.InvoiceService;
import com.example.demo.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.Valid;

@RestController
public class Controller {
	
	private static final Logger log = LoggerFactory.getLogger(Controller.class);
    @Autowired
    private UserService userService;

    @Autowired
    private InvoiceService InvoiceService;
    
    @PostMapping("/user")
    public ResponseEntity<Integer> createUser(@Valid @RequestBody userDto user) {
         int check = userService.createUser(user);
         if (check == 1) {
             return ResponseEntity.status(HttpStatus.ACCEPTED).body(1);
         } else {
             return ResponseEntity.status(HttpStatus.CONFLICT).body(check);
         }
    }



    @GetMapping("/user")
    public ResponseEntity<Integer> getUser(@Valid @RequestParam String userName, @RequestParam String password) {
        log.info("getUser: userName=" + userName + ", password=" + password);
        int check = userService.getUser(userName, password);
        if (check  > 0) {
            return ResponseEntity.ok(check);
        }
        else if(check == -1) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(404);
        }
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(401);

    }





    @PostMapping("/invoice")
    public ResponseEntity<Void> addInvoice(@Valid @RequestBody InvoiceDto invoice) {

        Boolean check = InvoiceService.addInvoice(invoice);
        if (check) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        else return ResponseEntity.status(HttpStatus.CONFLICT).build();

    }




    @GetMapping("/invoice/{userId}")
    public ResponseEntity<List<InvoiceDto>> getInvoicesByUserId(@PathVariable int userId) {
        log.info("Getting invoices by user id {}", userId);
         return ResponseEntity.ok(InvoiceService.getInvoicesByUserId(userId));
    }





    @GetMapping("/singleinvoice/{invocieId}")
    public ResponseEntity<InvoiceDto> getInvoicesByInvoiceId(@PathVariable int invocieId) {
        log.info("Getting invoices by invoice id {}", invocieId);
        InvoiceDto invoice = InvoiceService.getInvoiceByInvoiceId(invocieId);
        return ResponseEntity.ok(invoice);
    }



    @PutMapping("/invoice/{id}")
    public ResponseEntity<Void> updateInvoice(@PathVariable("id") int id,@Valid @RequestBody InvoiceDto invoice)  {
        boolean check = InvoiceService.updateInvoice(id, invoice);
        if(check) return ResponseEntity.ok().build();
        else throw new InvoiceNotFound(id);
    }






    @DeleteMapping("/invoice/{id}")
    public ResponseEntity<Integer> deleteInvoice(@PathVariable("id") int id) {
        log.info("Deleting invoice with id {}", id);
        int deletedId = InvoiceService.deleteInvoice(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(deletedId);
    }


}
