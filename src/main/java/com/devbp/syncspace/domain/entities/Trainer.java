package com.devbp.syncspace.domain.entities;

import com.devbp.syncspace.domain.UserStatus;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(columnDefinition = "TEXT")
    private List<String> specializations;

    @Column(columnDefinition = "TEXT")
    private List<String> certifications;

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

    public void addClass(Classes clazz) {

        if (clazz == null) {
            throw new IllegalArgumentException("Class cannot be null");
        }

        if (this.getUser().getStatus() != UserStatus.ACTIVE) {
            throw new IllegalArgumentException("Cannot add class to inactive user");
        }

        if (!classes.contains(clazz)) {
            classes.add(clazz);
            clazz.setTrainer(this);
        }

    }

    public void removeClass(Classes clazz) {

        if (clazz == null) {
            throw new IllegalArgumentException("Class cannot be null");
        }

        if (classes.contains(clazz)) {
            classes.remove(clazz);
            clazz.setTrainer(null);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return id == trainer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
