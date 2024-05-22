package com.example.demo.entityTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.packageEntities.Invoice;
import com.example.demo.packageEntities.InvoiceRepository;
import com.example.demo.packageEntities.User;
import com.example.demo.packageEntities.UserRepository;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
public class InvoiceRepoTest {
	
	private static final Logger log = LoggerFactory.getLogger(InvoiceRepoTest.class);

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private UserRepository userRepository;


    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveInvoiceTest() {


        User user = new User();
        user.setUserName("newuser2");
        user.setEmail("newuser@example.com2");
        user.setPassword("password367");
        user.setDateOfRegistration(new Date());
        userRepository.save(user);

        Invoice invoice = Invoice.builder()
                .clientName("new client2")
                .user(user)
                .amount(200.00)
                .invoiceDate(new Date())
                .description("new Invoice2")
                .build();

        invoiceRepository.save(invoice);

        int invoiceId = invoice.getInvoiceId();
        log.info("Invoice ID: " + invoiceId);
        assertThat(invoice.getUser().getUserId()).isGreaterThan(0);
    }


    @Test
    @Order(3)
    public void findInvoiceByInvoiceIdTest() {
        User user = userRepository.findById(1).orElse(null);
        assertThat(user).isNotNull();

        Optional<Invoice> invoices = invoiceRepository.findById(1);
        assertThat(invoices.isPresent()).isEqualTo(true);
    }
    
    @Test
    @Order(4)
    public void editInvoiceByInvoiceIdTest() {

        Invoice invoice = invoiceRepository.findById(1).orElse(null);
        assertThat(invoice).isNotNull();

        invoice.setClientName("updated client");
        invoiceRepository.save(invoice);

        Invoice updatedInvoice = invoiceRepository.findById(invoice.getInvoiceId()).orElse(null);

        assertThat(updatedInvoice.getClientName()).isEqualTo("updated client");

    }
    
    @Test
    @Order(5)
    public void deleteInvoiceByInvoiceIdTest() {

        invoiceRepository.deleteById(1);

        Invoice deletedInvoice = invoiceRepository.findById(1).orElse(null);
        assertThat(deletedInvoice).isNull();
    }
    
    @Test
    @Order(6)
    public void findInvoiceByNonExistentUserIdTest() {
        User user = userRepository.findById(999).orElse(null);
        assertThat(user).isNull();

        List<Invoice> invoices = invoiceRepository.findByUserUserId(999);
        assertThat(invoices).isEmpty();

    }
    
    
    @Test
    @Order(7)
    public void findInvoiceByNonExistentInvoiceIdTest() {
        Optional<Invoice> invoice = invoiceRepository.findById(999);
        assertThat(invoice.isPresent()).isFalse();
    }
    
    
    @Test
    @Order(8)
    public void editNonExistentInvoiceByInvoiceIdTest() {
        Invoice invoice = invoiceRepository.findById(-1).orElse(null);
        assertThat(invoice).isNull();


        Invoice updatedInvoice = invoiceRepository.findById(-1).orElse(null);
        assertThat(updatedInvoice).isNull();
    }
    
    
    @Test
    @Order(9)
    public void deleteNonExistentInvoiceByInvoiceIdTest() {
        Invoice invoice = invoiceRepository.findById(999).orElse(null);
        assertThat(invoice).isNull();

        if (invoice != null) {
            invoiceRepository.deleteById(999);
        }

        Invoice deletedInvoice = invoiceRepository.findById(999).orElse(null);
        assertThat(deletedInvoice).isNull();
    }

    
    @Test
    @Order(2)

    public void findInvoiceByUserIdTest() {
        User u9 = new User();
        u9.setUserName("testuser99");
        u9.setEmail("testuser@example.com");
        u9.setPassword("password");
        u9.setDateOfRegistration(new Date());
        userRepository.save(u9);

        Invoice invoice = Invoice.builder()
                .clientName("test client99")
                .user(u9)
                .amount(100.00)
                .invoiceDate(new Date())
                .description("Test Invoice99")
                .build();

        invoiceRepository.save(invoice);
        User user = userRepository.findByUserName("testuser99");
        assertThat(user).isNotNull();

        List<Invoice> invoices = invoiceRepository.findByUserUserId(user.getUserId());
        assertThat(invoices.size()).isGreaterThan(0);
    }

}
