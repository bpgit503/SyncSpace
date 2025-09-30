package com.devbp.syncspace.domain.dtos;

import com.devbp.syncspace.domain.BookingStatus;
import com.devbp.syncspace.domain.PaymentStatus;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBookingRequest {

    @Positive(message = "Client ID is required")
    private long clientId;

    @Positive(message = "Class ID is required")
    private long classId;

    private BookingStatus bookingStatus;

    @PositiveOrZero
    private double pricePaid;

    private PaymentStatus paymentStatus;

    private String notes;
}
