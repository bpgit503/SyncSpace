package com.devbp.syncspace.domain.dtos;

import com.devbp.syncspace.domain.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceItemsResponseDto {

    private long id;
    private BigDecimal totalPrice;
    private int quantity;
    private BigDecimal unitPrice;
    private String description;

    private long bookingId;
    private String bookingStatus;
    private LocalDateTime bookingDate;
    private PaymentStatus paymentStatus;
    private String notes;


}
