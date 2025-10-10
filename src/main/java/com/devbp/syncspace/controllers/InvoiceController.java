package com.devbp.syncspace.controllers;

import com.devbp.syncspace.domain.dtos.CreateInvoiceRequest;
import com.devbp.syncspace.domain.dtos.InvoiceResponseDto;
import com.devbp.syncspace.domain.dtos.UpdateInvoiceRequest;
import com.devbp.syncspace.domain.mappers.InvoiceMapper;
import com.devbp.syncspace.services.InvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public ResponseEntity<InvoiceResponseDto> createInvoice(@Valid @RequestBody CreateInvoiceRequest requestDto) {

        InvoiceResponseDto dto = invoiceMapper.toDto(invoiceService.createInvoice(requestDto));

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<InvoiceResponseDto> createInvoiceForSingleBooking(@PathVariable Long id) {

        InvoiceResponseDto dto = invoiceMapper.toDto(invoiceService.createInvoiceForSingleBooking(id));

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<InvoiceResponseDto> updateInvoice(@Valid @RequestBody UpdateInvoiceRequest requestDto) {

        InvoiceResponseDto dto = invoiceMapper.toDto(invoiceService.updateInvoice(requestDto));

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<InvoiceResponseDto> deleteInvoice(@PathVariable Long id) {

        invoiceService.deleteInvoiceById(id);

        return ResponseEntity.noContent().build();
    }
}
