package com.devbp.syncspace.controllers;

import com.devbp.syncspace.domain.dtos.*;
import com.devbp.syncspace.domain.mappers.BookingMapper;
import com.devbp.syncspace.services.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final BookingMapper bookingMapper;


    @GetMapping
    public ResponseEntity<List<BookingResponseDto>> getAllBookings() {
        List<BookingResponseDto> allBookings = bookingService.getAllBookings().stream()
                .map(bookingMapper::toDto)
                .toList();

        return ResponseEntity.ok(allBookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDto> getBookingById(@PathVariable Long id) {

        BookingResponseDto dto = bookingMapper.toDto(bookingService.getBookingById(id));

        return ResponseEntity.ok(dto);

    }

    @PostMapping
    public ResponseEntity<BookingResponseDto> createClass(@Valid @RequestBody CreateBookingRequest createBookingRequest) {

        BookingResponseDto dto = bookingMapper.toDto(bookingService.createBooking(createBookingRequest));

        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingResponseDto> updatedBookingById(@PathVariable Long id, @Valid @RequestBody UpdateBookingRequest updateBookingRequest) {

        BookingResponseDto dto = bookingMapper.toDto(bookingService.updateBooking(id, updateBookingRequest));

        return ResponseEntity.ok(dto);

    }


}
