package com.devbp.syncspace.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "invoice_items") // add unique constraint for client and class id?
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Bookings bookings;

    @NotNull
    @Column(nullable = false)
    private String description;

    @Column(name = "quantity")
    private int quantity = 1;

    @NotNull
    @Column(name = "unit_price", nullable = false)
    private double unitPrice;

    @NotNull
    @Column(name = "total_price", nullable = false)
    private double totalPrice;


}
