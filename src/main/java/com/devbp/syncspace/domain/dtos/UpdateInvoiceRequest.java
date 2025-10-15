package com.devbp.syncspace.domain.dtos;

import com.devbp.syncspace.domain.InvoiceStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateInvoiceRequest {

    @NotNull(message = "Invoice ID is required")
    private long id;

    private String invoiceNumber;

    @FutureOrPresent
    private LocalDate dueDate;

    private InvoiceStatus invoiceStatus;

    private LocalDateTime paymentDate;

    private String paymentMethod;

    @NotNull(message = "Note is required. What are you updating and why")
    private String notes;


}
