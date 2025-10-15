package com.devbp.syncspace.domain.mappers;

import com.devbp.syncspace.domain.dtos.InvoiceResponseDto;
import com.devbp.syncspace.domain.entities.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {UserMapper.class, InvoiceItemsMapper.class})
public interface InvoiceMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "dueDate", source = "dueDate")
    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "clientName",
            expression = "java(invoice.getClient().getFirstName() + \" \" + invoice.getClient().getLastName())")
    @Mapping(target = "clientEmail", source = "client.email")
    @Mapping(target = "items", source = "items")
    @Mapping(target = "createdAt", source = "createdAt")
    InvoiceResponseDto toDto(Invoice invoice);
}
