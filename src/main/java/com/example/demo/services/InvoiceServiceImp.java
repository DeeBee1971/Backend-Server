package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.ExceptionHandling.InvoiceNotFound;
import com.example.demo.ExceptionHandling.UserNotFound;
import com.example.demo.dataTransferObjects.InvoiceDto;
import com.example.demo.packageEntities.Invoice;
import com.example.demo.packageEntities.InvoiceRepository;
import com.example.demo.packageEntities.User;
import com.example.demo.packageEntities.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class InvoiceServiceImp implements InvoiceService{
	
	private static final Logger log = LoggerFactory.getLogger(InvoiceServiceImp.class);

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private UserRepository userRepository;

    public Boolean addInvoice(@RequestBody InvoiceDto invoiceDto) {
        int userId = invoiceDto.getUserId();
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new UserNotFound(userId);
        }
        User user = userOptional.get();

        Invoice invoice = new Invoice(0, user, invoiceDto.getClientName(), invoiceDto.getAmount(),invoiceDto.getInvoiceDate(), invoiceDto.getDescription());
        log.info("Invoice added " );
        invoiceRepository.save(invoice);
        return true;
    }

        public List<InvoiceDto> getInvoicesByUserId(int userId) {

        log.info(" getInvoicesByUserId "+userId);
        List<Invoice> invoices = invoiceRepository.findByUserUserId(userId);
        log.info("@invoice return check");
        if (invoices.isEmpty()) {
            throw new UserNotFound(userId);
        } else {
            List<InvoiceDto> invoiceDtos = new ArrayList<>();
            for (Invoice invoice : invoices) {
                InvoiceDto invoiceDto = InvoiceDto.builder()
                        .InvoiceId(invoice.getInvoiceId())
                        .clientName(invoice.getClientName())
                        .amount(invoice.getAmount())
                        .InvoiceDate(invoice.getInvoiceDate())
                        .description(invoice.getDescription())
                        .userId(invoice.getUser() != null ? invoice.getUser().getUserId() : null)
                        .build();
                invoiceDtos.add(invoiceDto);
            }
             return invoiceDtos;
        }
    }

    public Boolean updateInvoice(int id, InvoiceDto invoiceDto) {

        Optional<Invoice> existingInvoiceOptional = invoiceRepository.findById(id);
        if (existingInvoiceOptional.isPresent()) {
            Invoice existingInvoice = existingInvoiceOptional.get();

            existingInvoice.setClientName(invoiceDto.getClientName());
            existingInvoice.setAmount(invoiceDto.getAmount());
            existingInvoice.setInvoiceDate(invoiceDto.getInvoiceDate());
            existingInvoice.setDescription(invoiceDto.getDescription());

            invoiceRepository.save(existingInvoice);
            return true;
        } else {
            throw new InvoiceNotFound(id);
        }
    }

    public int deleteInvoice(int invoiceid) {
        if (invoiceRepository.existsById(invoiceid)) {
            invoiceRepository.deleteById(invoiceid);
            log.info("Invoice deleted:"+invoiceid);
            return invoiceid;
        } else {
            throw new InvoiceNotFound(invoiceid);
        }
    }

    public InvoiceDto getInvoiceByInvoiceId(int invoiceId) {

        log.info(" getInvoicesByInvoiceId " + invoiceId);
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(invoiceId);
        log.info("------------",optionalInvoice);
        if (optionalInvoice.isEmpty()) {
            throw new InvoiceNotFound(invoiceId);
        }

        Invoice invoice = optionalInvoice.get();

        InvoiceDto invoiceDto = InvoiceDto.builder()
                .InvoiceId(invoice.getInvoiceId())
                .InvoiceDate(invoice.getInvoiceDate())
                .clientName(invoice.getClientName())
                .amount(invoice.getAmount())
                .description(invoice.getDescription())
                .build();

        return invoiceDto;

    }


}
