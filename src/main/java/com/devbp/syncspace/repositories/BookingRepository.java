package com.devbp.syncspace.repositories;

import com.devbp.syncspace.domain.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    boolean existsByClient_IdAndClazz_Id(long clientId, long clazzId);
}
