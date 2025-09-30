package com.devbp.syncspace.services;

import com.devbp.syncspace.domain.BookingStatus;
import com.devbp.syncspace.domain.dtos.CreateBookingRequest;
import com.devbp.syncspace.domain.dtos.UpdateBookingRequest;
import com.devbp.syncspace.domain.entities.Booking;

import java.util.List;

public interface BookingService {

    List<Booking> getAllBookings();

    Booking getBookingById(long id);

    Booking createBooking( CreateBookingRequest createBookingRequest);

    Booking updateBooking(long id, UpdateBookingRequest updateBookingRequest);

    Booking confirmBookingStatus(long id, BookingStatus bookingStatus);

    Booking cancelBookingStatus(long id, BookingStatus bookingStatus);

    Booking completeBookingStatus(long id, BookingStatus bookingStatus);

    void deleteBooking( long id);

}
