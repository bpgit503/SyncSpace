package com.devbp.syncspace.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "trainers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "User id is required")
    @Column(name = "user_id")
    private long userId;

    @Column(columnDefinition = "TEXT")
    private String specializations;

    @Column(columnDefinition = "TEXT")
    private String certifications;

    @Column(name = "contract_details",columnDefinition = "TEXT")
    private String contractDetails;

    @NotNull(message = "Earning Percentage is required")
    @Column(name = "earnings_percentage")
    private double earningsPercentage;

    @Column(columnDefinition = "TEXT")
    private String bio;

    private int experienceYears;

    @Column(name = "is_available")
    private boolean isAvailable;


}
