package com.devbp.syncspace.domain.dtos;

import com.devbp.syncspace.domain.InvoiceStatus;
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
public class InvoiceResponseDto {

    private long id;
    private UserResponseDto client;
    private String invoiceNumber;
    private String invoiceDate;
    private LocalDate dueDate;
    private double totalAmount;
    private double taxAmount;
    private InvoiceStatus invoiceStatus;
    private LocalDateTime paymentDate;
    private String paymentMethod;
    private InvoiceItemsResponseDto invoiceItemsResponseDto;
    private String notes;
    private LocalDateTime createdAt;
}
