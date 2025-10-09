package com.devbp.syncspace.services.Impl;

import com.devbp.syncspace.domain.InvoiceStatus;
import com.devbp.syncspace.domain.UserType;
import com.devbp.syncspace.domain.dtos.CreateInvoiceRequest;
import com.devbp.syncspace.domain.dtos.UpdateInvoiceRequest;
import com.devbp.syncspace.domain.entities.*;
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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class InvoiceServiceImpl implements InvoiceService {

    private static final int DEFAULT_PAYMENT_TERMS_DAYS = 30;
    private static final BigDecimal TAX_RATE = new BigDecimal("0.08");
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

        User client = userRepository.findById(creatDto.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + creatDto.getClientId(), "User"));

        if (client.getUserType() != UserType.CLIENT) {
            throw new InvalidUserTypeException("User is not a client");
        }

        List<Booking> bookings = validateAndFetchBookings(creatDto.getBookingIds(), client);

        checkBookingsNotAlreadyInvoiced(bookings);

        Invoice invoice = new Invoice();
        invoice.setClient(client);
        invoice.setInvoiceNumber(generateInvoiceNumber());
        invoice.setInvoiceDate(creatDto.getInvoiceDate() != null
                ? creatDto.getInvoiceDate()
                : LocalDate.now());
        invoice.setDueDate(creatDto.getDueDate() != null
                ? creatDto.getDueDate()
                : invoice.getInvoiceDate().plusDays(DEFAULT_PAYMENT_TERMS_DAYS));
        invoice.setInvoiceStatus(InvoiceStatus.PENDING);
        invoice.setNotes(creatDto.getNotes());

        List<InvoiceItems> invoiceItems = createInvoiceItems(bookings, invoice);
        invoice.setItems(invoiceItems);

        calculateInvoiceTotals(invoice);


        return invoiceRepository.save(null);
    }

    private void calculateInvoiceTotals(Invoice invoice) {
        BigDecimal invoiceTotal = new BigDecimal(0);

        for (InvoiceItems ii : invoice.getItems()) {
            invoiceTotal = invoiceTotal.add(BigDecimal.valueOf(ii.getTotalPrice()));
        }

        BigDecimal taxAmount = invoiceTotal.multiply(TAX_RATE)
                .divide(BigDecimal.ONE.add(TAX_RATE), 2, RoundingMode.HALF_UP);

        invoice.setTotalAmount(invoiceTotal);
        invoice.setTaxAmount(taxAmount);

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

    private String generateInvoiceNumber() {

        int year = LocalDate.now().getYear();
        String prefix = "INV-" + year + "-";

        String lastInvoiceNumber = invoiceRepository.findLatestInvoiceNumber(prefix)
                .map(Invoice::getInvoiceNumber)
                .orElse(prefix + "00000");

        String sequence = lastInvoiceNumber.substring(prefix.length());
        int nextSequence = Integer.parseInt(sequence) + 1;

        return String.format("%s%05d", prefix, nextSequence);
    }

    private List<InvoiceItems> createInvoiceItems(List<Booking> bookings, Invoice invoice) {
        List<InvoiceItems> invoiceItems = new ArrayList<>();

        for (Booking booking : bookings) {
            InvoiceItems item = new InvoiceItems();
            item.setInvoice(invoice);
            item.setBooking(booking);

            Classes clazz = booking.getClazz();
            String description = String.format("%s - %s at %s",
                    clazz.getClassType().getClassName(),
                    clazz.getScheduledDate().format(DateTimeFormatter.ofPattern("MMM dd, yyyy")),
                    clazz.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")));

            item.setDescription(description);
            item.setQuantity(1);
            item.setUnitPrice(booking.getPricePaid());
            item.setTotalPrice(booking.getPricePaid());

            invoiceItems.add(item);

        }


        return invoiceItems;
    }

}
