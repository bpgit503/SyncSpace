package com.devbp.syncspace.domain.entities;

import com.devbp.syncspace.domain.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import javax.lang.model.element.QualifiedNameable;
import java.time.LocalDateTime;
import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TrainerEarnings that = (TrainerEarnings) o;
        return id == that.id && Double.compare(baseAmount, that.baseAmount) == 0 && Double.compare(earningPercentage, that.earningPercentage) == 0 && Double.compare(earningAmount, that.earningAmount) == 0 && Objects.equals(trainer, that.trainer) && Objects.equals(clazz, that.clazz) && paymentStatus == that.paymentStatus && Objects.equals(paymentDate, that.paymentDate) && Objects.equals(calculatedAt, that.calculatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, trainer, clazz, baseAmount, earningPercentage, earningAmount, paymentStatus, paymentDate, calculatedAt);
    }
}
