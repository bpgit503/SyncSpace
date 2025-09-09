package com.devbp.syncspace.domain.entities;

import com.devbp.syncspace.domain.UserStatus;
import com.devbp.syncspace.domain.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "First name is required")
    @Column(name = "first_name",nullable = false)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column( name = "phone", length = 20)
    private String phoneNumber;

    @Past(message = "Birthday must be in the past")
    @Column(name = "date_of_birth")
    private LocalDateTime dateOfBirth;

    @Column(columnDefinition = "TEXT")
    private String address;

    @NotNull(message = "User type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    private UserType userType;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status =  UserStatus.ACTIVE;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updateAt;
}
