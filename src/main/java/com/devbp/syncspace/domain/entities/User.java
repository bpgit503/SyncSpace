package com.devbp.syncspace.domain.entities;

import com.devbp.syncspace.domain.BookingStatus;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "First name is required")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone", length = 20)
    private String phoneNumber;

    @Past(message = "Birthday must be in the past")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(columnDefinition = "TEXT")
    private String address;

    @NotNull(message = "User type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    private UserType userType;

    @NotNull(message = "User status is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private UserStatus status = UserStatus.ACTIVE;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Bookings> bookings = new ArrayList<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Invoice> invoices = new ArrayList<>();

    public void addBooking(Bookings booking) {

        if (booking == null) {
            throw new IllegalArgumentException("Booking cannot be null");
        }

        if (this.getStatus() != UserStatus.ACTIVE) {
            throw new IllegalArgumentException("Cannot add booking to inactive user");
        }

        if (!bookings.contains(booking)) {

            bookings.add(booking);
            booking.setClient(this);
        }

    }

    public void removeBooking(Bookings booking) {
        if (booking == null) {
            throw new IllegalArgumentException("Booking cannot be null");
        }

        if (bookings.contains(booking)) {
            bookings.remove(booking);
            booking.setClient(null);
        }
    }

    public void addInvoice(Invoice invoice) {
        if (invoice == null) {
            throw new IllegalArgumentException("invoice cannot be null");
        }

        if (this.getStatus() != UserStatus.ACTIVE) {
            throw new IllegalArgumentException("Cannot add invoice to inactive user");
        }

        if (!invoices.contains(invoice)) {
            invoices.add(invoice);
            invoice.setClient(this);
        }
    }

    public void removeInvoice(Invoice invoice) {

        if (invoice == null) {
            throw new IllegalArgumentException("invoice cannot be null");
        }

        if (invoices.contains(invoice)) {
            invoices.remove(invoice);
            invoice.setClient(null);
        }

    }

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", status=" + status +
                ", userType=" + userType +
                '}';
    }
}
