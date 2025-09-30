package com.devbp.syncspace.domain.dtos;

import com.devbp.syncspace.domain.BookingStatus;
import com.devbp.syncspace.domain.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponseDto {

    private long id;
    private UserResponseDto client;
    private ClassResponseDto clazz;
    private LocalDateTime bookingDate;
    private BookingStatus bookingStatus;
    private double pricePaid;
    private PaymentStatus paymentStatus;
    private String notes;
}
