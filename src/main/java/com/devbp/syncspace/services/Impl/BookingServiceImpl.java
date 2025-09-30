package com.devbp.syncspace.services.Impl;

import com.devbp.syncspace.domain.BookingStatus;
import com.devbp.syncspace.domain.dtos.CreateBookingRequest;
import com.devbp.syncspace.domain.dtos.UpdateBookingRequest;
import com.devbp.syncspace.domain.entities.Booking;
import com.devbp.syncspace.exceptions.ResourceNotFoundException;
import com.devbp.syncspace.repositories.BookingRepository;
import com.devbp.syncspace.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));
    }

    @Override
    public Booking createBooking(CreateBookingRequest createBookingRequest) {
        return null;
    }

    @Override
    public Booking updateBooking(long id, UpdateBookingRequest updateBookingRequest) {
        return null;
    }

    @Override
    public Booking confirmBookingStatus(long id, BookingStatus bookingStatus) {
        return null;
    }

    @Override
    public Booking cancelBookingStatus(long id, BookingStatus bookingStatus) {
        return null;
    }

    @Override
    public Booking completeBookingStatus(long id, BookingStatus bookingStatus) {
        return null;
    }

    @Override
    public void deleteBooking(long id) {

    }
}
