package com.devbp.syncspace.controllers;

import com.devbp.syncspace.domain.dtos.BookingResponseDto;
import com.devbp.syncspace.domain.dtos.ClassResponseDto;
import com.devbp.syncspace.domain.mappers.BookingMapper;
import com.devbp.syncspace.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
