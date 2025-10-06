package com.devbp.syncspace.services.Impl;

import com.devbp.syncspace.domain.InvoiceStatus;
import com.devbp.syncspace.domain.dtos.CreateInvoiceRequest;
import com.devbp.syncspace.domain.dtos.UpdateInvoiceRequest;
import com.devbp.syncspace.domain.entities.Invoice;
import com.devbp.syncspace.domain.entities.InvoiceItems;
import com.devbp.syncspace.domain.entities.User;
import com.devbp.syncspace.exceptions.ResourceNotFoundException;
import com.devbp.syncspace.repositories.InvoiceItemsRepository;
import com.devbp.syncspace.repositories.InvoiceRepository;
import com.devbp.syncspace.repositories.UserRepository;
import com.devbp.syncspace.services.InvoiceNumberGenerator;
import com.devbp.syncspace.services.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceNumberGenerator invoiceNumberGenerator;
    private final InvoiceItemsRepository invoiceItemsRepository;
    private final UserRepository userRepository;

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice getInvoiceById(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with ID: " + id, "Invoice"));
    }

    //LOOK AT WHAT NEEDS TO BE DONE TO CREATE A PROPER INVOICE
    @Override
    public Invoice createInvoice(CreateInvoiceRequest createInvoiceRequest) {
        User user = userRepository.findById(createInvoiceRequest.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + createInvoiceRequest.getClientId(), "User"));

        InvoiceItems invoiceItem = invoiceItemsRepository.findById(createInvoiceRequest.getInvoiceItemId())
                .orElseThrow(() -> new ResourceNotFoundException("Invoice Item not found with ID: " + createInvoiceRequest.getInvoiceItemId(), "InvoiceItem"));

        Invoice invoice = new Invoice();
        invoice.setClient(user);

        invoice.setInvoiceNumber(invoiceNumberGenerator.generateInvoiceNumber());
        invoice.setTotalAmount(createInvoiceRequest.getTotalAmount());
        invoice.setTaxAmount(createInvoiceRequest.getTaxAmount());

        invoice.setTotalAmount(createInvoiceRequest.getTotalAmount());

        Optional.of(createInvoiceRequest.getTaxAmount())
                .ifPresent(invoice::setTaxAmount);

        Optional.ofNullable(createInvoiceRequest.getInvoiceStatus())
                .ifPresent(invoice::setInvoiceStatus);

        Optional.ofNullable(createInvoiceRequest.getPaymentDate())
                .ifPresent(invoice::setPaymentDate);

        Optional.ofNullable(createInvoiceRequest.getPaymentMethod())
                .ifPresent(invoice::setPaymentMethod);

        Optional.ofNullable(createInvoiceRequest.getDueDate())
                .ifPresent(invoice::setDueDate);

        // must create inovice items list look at how to make invoiceitems

        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice updateInvoice(UpdateInvoiceRequest updateInvoiceRequest) {
        return null;
    }

    @Override
    public void deleteInvoiceById(long id) {

    }
}
