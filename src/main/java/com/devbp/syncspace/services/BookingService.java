package com.devbp.syncspace.services;

import com.devbp.syncspace.domain.dtos.CreateBookingRequest;
import com.devbp.syncspace.domain.dtos.UpdateBookingRequest;
import com.devbp.syncspace.domain.dtos.UpdateBookingStatusRequestDto;
import com.devbp.syncspace.domain.entities.Booking;

import java.util.List;

public interface BookingService {

    List<Booking> getAllBookings();

    Booking getBookingById(long id);

    Booking createBooking( CreateBookingRequest createBookingRequest);

    Booking updateBooking(long id, UpdateBookingRequest updateBookingRequest);

    Booking updateBookingStatus(long id, UpdateBookingStatusRequestDto updateBookingStatusRequestDto);

    void deleteBooking( long id);

    boolean checkIfBookingExists(long bookingId, long clientId, long classId);

}
