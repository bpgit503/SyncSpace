package com.devbp.syncspace.domain.dtos;

import com.devbp.syncspace.domain.InvoiceStatus;
import jakarta.persistence.Column;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
public class CreateInvoiceRequest {

    @NotNull(message = "Client ID is required")
    private long clientId;

    @NotNull(message = "Invoice item ID is required")
    private long invoiceItemId;

    @NotNull(message = "Due Date is required")
    private LocalDate dueDate;

    @NotNull(message = "Total amount is required")
    @PositiveOrZero
    private double totalAmount;

    @PositiveOrZero
    private double taxAmount;

    private InvoiceStatus invoiceStatus;

    private LocalDateTime paymentDate;

    private String paymentMethod;

    private String notes;

    @AssertTrue(message = "Payment date and method must not be null when status is PAID")
    public boolean isValidPayment() {
        if(invoiceStatus == InvoiceStatus.PAID) {
            return paymentDate != null && paymentMethod != null && notes != null;
        }
        return true;
    }

}
