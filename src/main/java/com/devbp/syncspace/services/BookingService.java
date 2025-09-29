package com.devbp.syncspace.services;

import com.devbp.syncspace.domain.BookingStatus;
import com.devbp.syncspace.domain.dtos.CreateBookingRequest;
import com.devbp.syncspace.domain.dtos.UserResponseDto;
import com.devbp.syncspace.domain.entities.Booking;

public interface BookingService {

    Booking findAllBookings();

    Booking getBookingById();

    Booking createBooking( CreateBookingRequest createBookingRequest);

    Booking updateBooking(long id, UserResponseDto.UpdtaeBookingRequest updtaeBookingRequest);

    Booking confirmBookingStatus(long id, BookingStatus bookingStatus);

    Booking cancelBookingStatus(long id, BookingStatus bookingStatus);

    Booking completeBookingStatus(long id, BookingStatus bookingStatus);

    void deleteBooking( long id);

}
