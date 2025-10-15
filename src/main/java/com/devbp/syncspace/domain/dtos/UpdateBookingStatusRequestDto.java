package com.devbp.syncspace.domain.dtos;

import com.devbp.syncspace.domain.BookingStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateBookingStatusRequestDto {

    @Positive(message = "Client ID is required")
    private long clientId;

    @Positive(message = "Class ID is required")
    private long classId;

    @NotNull(message = "Booking Status is required")
    private BookingStatus bookingStatus;

    @NotNull(message = "Notes is required")
    private String notes;

}
