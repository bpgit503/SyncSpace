package com.devbp.syncspace.services;

import org.springframework.stereotype.Service;

@Service
public interface InvoiceNumberGenerator {

    String generateInvoiceNumber();

}
