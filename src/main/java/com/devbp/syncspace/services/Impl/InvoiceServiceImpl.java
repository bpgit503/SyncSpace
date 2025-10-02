package com.devbp.syncspace.services.Impl;

import com.devbp.syncspace.domain.dtos.CreateInvoiceRequest;
import com.devbp.syncspace.domain.dtos.UpdateInvoiceRequest;
import com.devbp.syncspace.domain.entities.Invoice;
import com.devbp.syncspace.services.InvoiceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Override
    public List<Invoice> getAllInvoices() {
        return List.of();
    }

    @Override
    public Invoice getInvoiceById(long id) {
        return null;
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
