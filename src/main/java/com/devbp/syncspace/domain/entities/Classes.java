package com.devbp.syncspace.domain.entities;

import com.devbp.syncspace.domain.ClassStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "classes")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Classes {

    //A way to validate that the start time < end time?

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_type_id", nullable = false)
    private ClassType classType;

    //bidirectional association: Classes know about it trainers
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;

    @FutureOrPresent(message ="Scheduled date must be in the future or present")
    @NotNull(message = "Scheduled date is required")
    @Column(name = "scheduled_date", nullable = false)
    private LocalDate scheduledDate;

    @NotNull(message = "Start time is required")
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @NotNull(message = "Max capacity is required")
    @Positive
    @Column(name = "max_capacity", nullable = false)
    private int maxCapacity;

    @PositiveOrZero
    @Column(name = "current_capacity")
    private int currentCapacity = 0;

    @NotNull(message = "Class status is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ClassStatus classStatus = ClassStatus.SCHEDULED;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


}
