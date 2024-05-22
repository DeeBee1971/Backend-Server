package com.example.demo.services;

import java.util.List;

import com.example.demo.ExceptionHandling.InvoiceNotFound;
import com.example.demo.ExceptionHandling.UserNotFound;
import com.example.demo.dataTransferObjects.InvoiceDto;

public interface InvoiceService {
	
	Boolean addInvoice(InvoiceDto invoiceDto) throws UserNotFound;

    List<InvoiceDto> getInvoicesByUserId(int userId) throws UserNotFound;

    Boolean updateInvoice(int invoiceId, InvoiceDto invoiceDto) throws InvoiceNotFound;

    int deleteInvoice(int invoiceId) throws InvoiceNotFound;

    InvoiceDto getInvoiceByInvoiceId(int invoiceId) throws InvoiceNotFound;

}

