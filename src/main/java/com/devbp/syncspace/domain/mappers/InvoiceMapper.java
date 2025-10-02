package com.devbp.syncspace.domain.mappers;

import com.devbp.syncspace.domain.dtos.CreateInvoiceRequest;
import com.devbp.syncspace.domain.entities.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = UserMapper.class)
public interface InvoiceMapper {

    CreateInvoiceRequest toDto(Invoice invoice);
}
