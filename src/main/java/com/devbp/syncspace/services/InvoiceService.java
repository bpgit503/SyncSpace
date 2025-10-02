package com.devbp.syncspace.services;

import com.devbp.syncspace.domain.dtos.CreateInvoiceRequest;
import com.devbp.syncspace.domain.dtos.UpdateInvoiceRequest;
import com.devbp.syncspace.domain.entities.Invoice;

import java.util.List;


public interface InvoiceService {

    List<Invoice> getAllInvoices();

    Invoice getInvoiceById(long id);

    Invoice createInvoice(CreateInvoiceRequest createInvoiceRequest);

    Invoice updateInvoice(UpdateInvoiceRequest updateInvoiceRequest);

    void deleteInvoiceById(long id);
}
