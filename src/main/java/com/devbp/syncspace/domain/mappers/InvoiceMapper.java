package com.devbp.syncspace.domain.mappers;

import com.devbp.syncspace.domain.dtos.InvoiceResponseDto;
import com.devbp.syncspace.domain.entities.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {UserMapper.class, InvoiceItemsMapper.class})
public interface InvoiceMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "dueDate", target = "dueDate")
    InvoiceResponseDto toDto(Invoice invoice);
}
