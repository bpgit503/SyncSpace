package com.devbp.syncspace.repositories;

import com.devbp.syncspace.domain.entities.InvoiceItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceItemsRepository extends JpaRepository<InvoiceItems, Long> {

    @Query("Select ii.booking.id from InvoiceItems ii where ii.booking.id in :bookingIds")
    List<Long> findAlreadyInvoiceBookings(@Param("bookingIds") List<Long> bookingIds);
}
