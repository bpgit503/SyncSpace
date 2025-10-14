package com.devbp.syncspace.domain.dtos;

import com.devbp.syncspace.domain.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceItemsResponseDto {

    private long id;
    private String description;
    private BigDecimal totalPrice;
    private int quantity;
    private BigDecimal unitPrice;

    private String bookingStatus;
    private PaymentStatus paymentStatus;
    private String notes;


}
