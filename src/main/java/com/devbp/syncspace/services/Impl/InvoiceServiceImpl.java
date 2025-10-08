package com.devbp.syncspace.services.Impl;

import com.devbp.syncspace.domain.UserType;
import com.devbp.syncspace.domain.dtos.CreateInvoiceRequest;
import com.devbp.syncspace.domain.dtos.UpdateInvoiceRequest;
import com.devbp.syncspace.domain.entities.Invoice;
import com.devbp.syncspace.domain.entities.User;
import com.devbp.syncspace.exceptions.InvalidUserTypeException;
import com.devbp.syncspace.exceptions.ResourceNotFoundException;
import com.devbp.syncspace.repositories.InvoiceItemsRepository;
import com.devbp.syncspace.repositories.InvoiceRepository;
import com.devbp.syncspace.repositories.UserRepository;
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

    /* Invoice Flow
    Fetch user
    validatie User = type client
    validate and fetch bookings - method
        check if booking != null || empty
        find all bookings by ids
        found booking size = booking ids.size
        filter for invalid bookings = clients bookings
        check if empty
        filter for non completed bookings
        warn

     Validate the given bookings have not been invoice already
     cr8 invoice
     cr8 invoiceItems and set them
     calulate total amount through invoice items
     update booking payment status
     save invoice

     */
    @Override
    public Invoice createInvoice(CreateInvoiceRequest creatDto) {

        User user = userRepository.findById(creatDto.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + creatDto.getClientId(), "User"));

        if(user.getUserType() != UserType.CLIENT){
            throw new InvalidUserTypeException("User is not a client");
        }





        return invoiceRepository.save(null);
    }

    @Override
    public Invoice updateInvoice(UpdateInvoiceRequest updateInvoiceRequest) {
        return null;
    }

    @Override
    public void deleteInvoiceById(long id) {

    }
}
