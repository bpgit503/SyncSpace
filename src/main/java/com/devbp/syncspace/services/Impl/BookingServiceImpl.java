package com.devbp.syncspace.services.Impl;

import com.devbp.syncspace.domain.BookingStatus;
import com.devbp.syncspace.domain.dtos.CreateBookingRequest;
import com.devbp.syncspace.domain.dtos.UpdateBookingRequest;
import com.devbp.syncspace.domain.dtos.UpdateBookingStatusRequestDto;
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

        Classes clazz = classRepository.findClassesById_AndIsActive(classId)
                .orElseThrow(() -> new ResourceNotFoundException("No Active Class found with id: " + classId));

        Booking newBooking = new Booking();
        newBooking.setClient(bookingClient);
        newBooking.setClazz(clazz);
        newBooking.setPricePaid(createBookingRequest.getPricePaid());
        newBooking.setNotes(createBookingRequest.getNotes());

        Optional.ofNullable(createBookingRequest.getPaymentStatus())
                .ifPresent(newBooking::setPaymentStatus);

        bookingClient.addBooking(newBooking);

        clazz.setCurrentCapacity(clazz.getCurrentCapacity() + 1);

        return bookingRepository.save(newBooking);
    }

    @Transactional
    @Override
    public Booking updateBooking(long id, UpdateBookingRequest updateBookingRequest) {

        long clientId = updateBookingRequest.getClientId();
        long classId = updateBookingRequest.getClassId();

        checkIfBookingExists(id, clientId, classId);

        Booking existingBooking = getBookingById(id);

        Optional.ofNullable(updateBookingRequest.getBookingStatus())
                .ifPresent(existingBooking::setBookingStatus);

        Optional.of(updateBookingRequest.getPricePaid())
                .ifPresent(existingBooking::setPricePaid);

        Optional.of(updateBookingRequest.getPaymentStatus())
                .ifPresent(existingBooking::setPaymentStatus);

        Optional.ofNullable(updateBookingRequest.getNotes())
                .ifPresent(newNotes -> {
                    String existing = Optional.ofNullable(existingBooking.getNotes()).orElse("");
                    String updated = existing.isEmpty() ? newNotes : existing + "\n" + newNotes;
                    existingBooking.setNotes(updated);
                });

        return bookingRepository.save(existingBooking);
    }

    @Transactional
    @Override
    public Booking updateBookingStatus(long id, UpdateBookingStatusRequestDto updateBookingStatusRequestDto) {

        checkIfBookingExists(id, updateBookingStatusRequestDto.getClientId(), updateBookingStatusRequestDto.getClassId());

        BookingStatus bookingStatus = updateBookingStatusRequestDto.getBookingStatus();

        Booking existingBooking = getBookingById(id);

        existingBooking.setBookingStatus(bookingStatus);
        existingBooking.setNotes(existingBooking.getNotes() +"\n"+ updateBookingStatusRequestDto.getNotes());

        return bookingRepository.save(existingBooking);
    }

    @Override
    public void deleteBooking(long id) {

        bookingRepository.delete(getBookingById(id));
    }

    @Override
    public boolean checkIfBookingExists(long bookingId, long clientId, long classId) {

        if (!bookingRepository.existsBookingByIdAndClientIdAndClazzId(bookingId, clientId, classId)) {
            throw new ResourceNotFoundException("Booking not found with booking id: " + bookingId + " client id: " + clientId + " class id: " + classId, "Booking Error");
        }
        return true;
    }


}
