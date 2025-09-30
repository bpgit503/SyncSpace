package com.devbp.syncspace.services.Impl;

import com.devbp.syncspace.domain.BookingStatus;
import com.devbp.syncspace.domain.dtos.CreateBookingRequest;
import com.devbp.syncspace.domain.dtos.UpdateBookingRequest;
import com.devbp.syncspace.domain.entities.Booking;
import com.devbp.syncspace.domain.entities.Classes;
import com.devbp.syncspace.domain.entities.User;
import com.devbp.syncspace.exceptions.EntityAlreadyExistsException;
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

    @Transactional
    @Override
    public Booking createBooking(CreateBookingRequest createBookingRequest) {
        long clientId = createBookingRequest.getClientId();
        long classId = createBookingRequest.getClassId();

        if (bookingRepository.existsByClient_IdAndClazz_Id(clientId, classId)) {
            throw new EntityAlreadyExistsException("Booking already exists with client id: " + clientId + " class id: " + classId, " Booking Error");
        }

        User bookingClient = userRepository.findActiveClientById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("No Active Client found with id: " + clientId));

        Classes classToBeBooked = classRepository.findClassesById_AndIsActive(classId)
                .orElseThrow(() -> new ResourceNotFoundException("No Active Class found with id: " + classId));

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

        long clientId = updateBookingRequest.getClientId();
        long classId = updateBookingRequest.getClassId();

        if (!bookingRepository.existsById(id)) {
            throw new ResourceNotFoundException("Booking not found with client id: " + clientId + " class id: " + classId, " Booking Error");
        }
        Booking exsitingBooking = getBookingById(id);

        Optional.ofNullable(updateBookingRequest.getBookingStatus())
                .ifPresent(exsitingBooking::setBookingStatus);

        Optional.of(updateBookingRequest.getPricePaid())
                .ifPresent(exsitingBooking::setPricePaid);

        Optional.of(updateBookingRequest.getPaymentStatus())
                .ifPresent(exsitingBooking::setPaymentStatus);

        Optional.ofNullable(updateBookingRequest.getNotes())
                .ifPresent(exsitingBooking::setNotes);

        return bookingRepository.save(exsitingBooking);
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
