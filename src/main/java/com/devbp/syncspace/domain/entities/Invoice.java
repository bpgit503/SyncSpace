package com.devbp.syncspace.domain.entities;

import com.devbp.syncspace.domain.InvoiceStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    @NotNull(message = "Invoice number is required")
    @Column(name = "invoice_number", nullable = false, unique = true)
    private String invoiceNumber;

    @CreationTimestamp
    @NotNull(message = "Invoice date is required")
    @Column(name = "invoice_date", nullable = false)
    private LocalDate invoiceDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @NotNull(message = "Total amount is required")
    @PositiveOrZero
    @Column(name = "total_amount", nullable = false)
    private double totalAmount;

    @PositiveOrZero
    @Column(name = "tax_amount")
    private double taxAmount = 0.0;

    @NotNull(message = "Invoice status is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private InvoiceStatus invoiceStatus = InvoiceStatus.PENDING;

    //check this sql timestamp mapped to what in java
    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "payment_method")
    private String paymentMethod;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<InvoiceItems> items = new ArrayList<>();

    @Column()
    private String notes;

    @CreationTimestamp
    @Column(name = "created_At")
    private LocalDateTime createAt;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(invoiceNumber, invoice.invoiceNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(invoiceNumber);
    }
}
