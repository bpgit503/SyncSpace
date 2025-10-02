package com.devbp.syncspace.services;

import com.devbp.syncspace.domain.dtos.CreateInvoiceItemRequest;
import com.devbp.syncspace.domain.dtos.UpdateInvoiceItemRequest;
import com.devbp.syncspace.domain.entities.InvoiceItems;

import java.util.List;

public interface InvoiceItemsService {

    List<InvoiceItems> getAllInvoiceItems();

    InvoiceItems getInvoiceItemById(long id);

    InvoiceItems createInvoiceItems(CreateInvoiceItemRequest createInvoiceItemRequest);

    InvoiceItems updatedInvoiceItems( UpdateInvoiceItemRequest updateInvoiceItemRequest);

    void deleteInvoiceItemsById(long id);
}
