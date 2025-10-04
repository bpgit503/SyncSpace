package com.devbp.syncspace.services.Impl;

import com.devbp.syncspace.domain.dtos.CreateInvoiceRequest;
import com.devbp.syncspace.domain.dtos.UpdateInvoiceRequest;
import com.devbp.syncspace.domain.entities.Invoice;
import com.devbp.syncspace.exceptions.ResourceNotFoundException;
import com.devbp.syncspace.repositories.InvoiceRepository;
import com.devbp.syncspace.services.InvoiceNumberGenerator;
import com.devbp.syncspace.services.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceNumberGenerator invoiceNumberGenerator;

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice getInvoiceById(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with ID: " + id, "Invoice"));
    }

    @Override
    public Invoice createInvoice(CreateInvoiceRequest createInvoiceRequest) {
        return null;
    }

    @Override
    public Invoice updateInvoice(UpdateInvoiceRequest updateInvoiceRequest) {
        return null;
    }

    @Override
    public void deleteInvoiceById(long id) {

    }
}
