package com.devbp.syncspace.domain.entities;

import com.devbp.syncspace.domain.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import javax.lang.model.element.QualifiedNameable;
import java.time.LocalDateTime;

@Entity
@Table(name = "trainer_earnings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainerEarnings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Classes clazz;

    @NotNull(message = "Base amount is required")
    @Column(name = "base_amount", nullable = false)
    private double baseAmount;

    @NotNull(message = "Earnings percentage is required")
    @Column(name = "earnings_percentage", nullable = false)
    private double earningPercentage;

    @NotNull(message = "Earning amount is required")
    @Column(name = "earnings_amount", nullable = false)
    private double earningAmount;

    @NotNull(message = "Payment Status is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "calculated_at")
    private LocalDateTime calculatedAt;

}
