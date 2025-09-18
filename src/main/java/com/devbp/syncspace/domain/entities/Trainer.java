package com.devbp.syncspace.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "trainers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Trainer {

    //TODO figure how TEXT[] fields map to jpa

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(columnDefinition = "TEXT")
    private String specializations;

    @Column(columnDefinition = "TEXT")
    private String certifications;

    @Column(name = "contract_details", columnDefinition = "TEXT")
    private String contractDetails;

    @NotNull(message = "Earning Percentage is required")
    @Column(name = "earnings_percentage")
    private double earningsPercentage;

    @Column(name = "hourly_rate")
    private double hourlyRate;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(name = "experience_years")
    private int experienceYears;

    @Column(name = "is_available")
    private boolean isAvailable;

    @OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Classes> classes = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return id == trainer.id && Double.compare(earningsPercentage, trainer.earningsPercentage) == 0 && Double.compare(hourlyRate, trainer.hourlyRate) == 0 && experienceYears == trainer.experienceYears && isAvailable == trainer.isAvailable && Objects.equals(user, trainer.user) && Objects.equals(specializations, trainer.specializations) && Objects.equals(certifications, trainer.certifications) && Objects.equals(contractDetails, trainer.contractDetails) && Objects.equals(bio, trainer.bio) && Objects.equals(classes, trainer.classes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, specializations, certifications, contractDetails, earningsPercentage, hourlyRate, bio, experienceYears, isAvailable, classes);
    }
}
