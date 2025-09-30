package com.devbp.syncspace.services.Impl;

import com.devbp.syncspace.domain.BookingStatus;
import com.devbp.syncspace.domain.dtos.CreateBookingRequest;
import com.devbp.syncspace.domain.dtos.UpdateBookingRequest;
import com.devbp.syncspace.domain.entities.Booking;
import com.devbp.syncspace.domain.entities.Classes;
import com.devbp.syncspace.domain.entities.User;
import com.devbp.syncspace.exceptions.ResourceNotFoundException;
import com.devbp.syncspace.repositories.BookingRepository;
import com.devbp.syncspace.repositories.ClassRepository;
import com.devbp.syncspace.repositories.UserRepository;
import com.devbp.syncspace.services.BookingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final ClassRepository classRepository;
    private final UserRepository userRepository;

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));
    }

    //user and classtype are active
    //do check that client cant book a class twice
    @Transactional
    @Override
    public Booking createBooking(CreateBookingRequest createBookingRequest) {
        User bookingClient = userRepository.findActiveClientById(createBookingRequest.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("No Active Client found with id: " + createBookingRequest.getClientId()));

        Classes classToBeBooked = classRepository.findClassesById_AndIsActive(createBookingRequest.getClassId())
                .orElseThrow(() -> new ResourceNotFoundException("No Active Class found with id: " + createBookingRequest.getClassId()));

        Booking newBooking = new Booking();
        newBooking.setClient(bookingClient);
        newBooking.setClazz(classToBeBooked);
        newBooking.setPricePaid(createBookingRequest.getPricePaid());
        newBooking.setNotes(createBookingRequest.getNotes());

        Optional.ofNullable(createBookingRequest.getPaymentStatus())
                .ifPresent(newBooking::setPaymentStatus);

        bookingClient.addBooking(newBooking);

        classToBeBooked.setCurrentCapacity(classToBeBooked.getCurrentCapacity() + 1);

        return bookingRepository.save(newBooking);
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
