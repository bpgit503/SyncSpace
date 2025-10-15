package com.devbp.syncspace.domain.dtos;

import com.devbp.syncspace.domain.InvoiceStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateInvoiceRequest {

    @NotNull(message = "Client ID is required")
    private long clientId;

    @NotNull(message = "Booking IDs are required")
    private List<Long> bookingIds;

    private LocalDate invoiceDate;

    private LocalDate dueDate;

    private InvoiceStatus invoiceStatus;

    private String notes;

}
