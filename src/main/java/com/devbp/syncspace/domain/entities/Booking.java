package com.devbp.syncspace.domain.entities;

import com.devbp.syncspace.domain.BookingStatus;
import com.devbp.syncspace.domain.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "bookings",
    uniqueConstraints = @UniqueConstraint(columnNames = {"client_id", "class_id"}))// add unique constraint for client and class id?
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", nullable = false)
    private Classes clazz;

    @FutureOrPresent(message = "Bookings can be only made in the future or present")
    @Column(name = "booking_date")
    private LocalDateTime bookingDate;

    @NotNull(message = "Booking Status is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BookingStatus bookingStatus = BookingStatus.CONFIRMED;

    @NotNull(message = "Paid price is required")
    @PositiveOrZero
    @Column(name = "price_paid", nullable = false)
    private double pricePaid;

    @NotNull(message = "Payment Status is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(client, booking.client) && Objects.equals(clazz, booking.clazz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, clazz);
    }
}
