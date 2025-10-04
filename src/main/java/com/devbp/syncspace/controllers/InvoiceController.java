package com.devbp.syncspace.controllers;

import com.devbp.syncspace.domain.dtos.InvoiceResponseDto;
import com.devbp.syncspace.domain.mappers.InvoiceMapper;
import com.devbp.syncspace.services.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/invoice")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final InvoiceMapper invoiceMapper;

    @GetMapping
    public ResponseEntity<List<InvoiceResponseDto>> getAllInvoices() {
        List<InvoiceResponseDto> allClassTypes = invoiceService.getAllInvoices().stream()
                .map(invoiceMapper::toDto)
                .toList();

        return ResponseEntity.ok(allClassTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponseDto> getClassTypeById(@PathVariable Long id) {

        InvoiceResponseDto dto = invoiceMapper.toDto(invoiceService.getInvoiceById(id));

        return ResponseEntity.ok(dto);

    }

}
