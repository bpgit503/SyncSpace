package com.devbp.syncspace.services.Impl;

import com.devbp.syncspace.domain.entities.Invoice;
import com.devbp.syncspace.repositories.InvoiceRepository;
import com.devbp.syncspace.services.InvoiceNumberGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class InvoiceNumberGeneratorImpl implements InvoiceNumberGenerator {

    private final InvoiceRepository invoiceRepository;

    @Override
    public String generateInvoiceNumber() {

        int year = LocalDate.now().getYear();
        String prefix = "INV-" + year + "-";

        String lastInvoiceNumber = invoiceRepository.findLatestInvoiceNumber(prefix)
                .map(Invoice::getInvoiceNumber)
                .orElse(prefix + "00000");

        String sequence = lastInvoiceNumber.substring(prefix.length());
        int nextSequence = Integer.parseInt(sequence) + 1;

        return String.format("%s%05d", prefix, nextSequence);
    }
}
