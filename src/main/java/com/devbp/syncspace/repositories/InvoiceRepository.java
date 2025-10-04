package com.devbp.syncspace.repositories;

import com.devbp.syncspace.domain.entities.Invoice;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query("SELECT i FROM Invoice i WHERE i.invoiceNumber LIKE concat(:invoiceNumber, '%') ORDER BY i.invoiceNumber DESC")
    Optional<Invoice> findLatestInvoiceNumber(@Param("invoiceNumber") String invoiceNumber);

}
