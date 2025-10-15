package com.devbp.syncspace.domain.dtos;

import com.devbp.syncspace.domain.BookingStatus;
import com.devbp.syncspace.domain.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponseDto {

    private long id;
    private LocalDateTime bookingDate;
    private BookingStatus bookingStatus;
    private double pricePaid;
    private PaymentStatus paymentStatus;
    private String notes;

    private Long clientId;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    private long classId;
    private LocalDate scheduledDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private int maxCapacity;
}
