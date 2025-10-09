package com.devbp.syncspace.services.Impl;

import com.devbp.syncspace.domain.UserType;
import com.devbp.syncspace.domain.dtos.CreateInvoiceRequest;
import com.devbp.syncspace.domain.dtos.UpdateInvoiceRequest;
import com.devbp.syncspace.domain.entities.Booking;
import com.devbp.syncspace.domain.entities.Invoice;
import com.devbp.syncspace.domain.entities.User;
import com.devbp.syncspace.exceptions.InvalidInvoiceException;
import com.devbp.syncspace.exceptions.InvalidUserTypeException;
import com.devbp.syncspace.exceptions.ResourceNotFoundException;
import com.devbp.syncspace.repositories.BookingRepository;
import com.devbp.syncspace.repositories.InvoiceItemsRepository;
import com.devbp.syncspace.repositories.InvoiceRepository;
import com.devbp.syncspace.repositories.UserRepository;
import com.devbp.syncspace.services.InvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final BookingRepository bookingRepository;
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
        sql check to if inovice already exists
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

        if (user.getUserType() != UserType.CLIENT) {
            throw new InvalidUserTypeException("User is not a client");
        }

        List<Booking> bookings = validateAndFetchBookings(creatDto.getBookingIds(), user);

        checkBookingsNotAlreadyInvoiced(bookings);


        return invoiceRepository.save(null);
    }

    @Override
    public Invoice updateInvoice(UpdateInvoiceRequest updateInvoiceRequest) {
        return null;
    }

    @Override
    public void deleteInvoiceById(long id) {

    }

    private List<Booking> validateAndFetchBookings(List<Long> bookingIds, User client) {
        if (bookingIds == null || bookingIds.isEmpty()) {
            throw new InvalidInvoiceException("At Least one booking must be included", "Invoice");
        }

        List<Booking> bookings = bookingRepository.findAllById(bookingIds);

        if (bookings.size() != bookingIds.size()) {
            throw new ResourceNotFoundException("One or more bookings not found", "Invoice - Fetch and Validate Bookings");
        }

        List<Booking> invalidBookings = bookings.stream()
                .filter(b -> b.getClient().getId().equals(client.getId()))
                .toList();

        if (!invalidBookings.isEmpty()) {
            throw new InvalidInvoiceException("Bookings do not belong to the specified client", "Invoice - Fetch and Validate Bookings");
        }

        return bookings;
    }

    private void checkBookingsNotAlreadyInvoiced(List<Booking> bookings) {
        List<Long> bookingIds = bookings.stream()
                .map(Booking::getId)
                .toList();

        List<Long> alreadyInvoicedBookingIds = invoiceItemsRepository.findAlreadyInvoiceBookings(bookingIds);

        if (!alreadyInvoicedBookingIds.isEmpty()) {
            throw new InvalidInvoiceException(
                    "The following bookings are already invoiced: " + alreadyInvoicedBookingIds);
        }
    }

}
