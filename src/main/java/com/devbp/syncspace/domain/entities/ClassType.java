package com.devbp.syncspace.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "class_types")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Class name is required")
    @Column(name = "name", nullable = false, unique = true, columnDefinition = "varchar(100)")
    private String className;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "Duration of class in minuets is required")
    @Column(name = "duration_minutes", nullable = false)
    private int durationMinutes = 60;

    @NotNull(message = "Group class setting is required") //** find better wording
    @Column(name = "is_group_class", nullable = false)
    private boolean isGroupClass = true;

    @NotNull(message = "Max capacity is required")
    @Positive
    @Column(name = "max_capacity", nullable = false)
    private int maxCapacity = 15;

    @NotNull(message = "Base price is required")
    @Positive
    @Column(name = "base_price", nullable = false)
    private double basePrice;

    @NotNull(message = "Group class setting is required") //** find better wording
    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
