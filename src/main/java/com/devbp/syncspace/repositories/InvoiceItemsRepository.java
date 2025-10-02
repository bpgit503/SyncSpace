package com.devbp.syncspace.repositories;

import com.devbp.syncspace.domain.entities.InvoiceItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceItemsRepository extends JpaRepository<InvoiceItems, Long> {
}
