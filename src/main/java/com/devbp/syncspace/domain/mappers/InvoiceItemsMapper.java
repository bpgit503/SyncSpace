package com.devbp.syncspace.domain.mappers;

import com.devbp.syncspace.domain.dtos.InvoiceItemsResponseDto;
import com.devbp.syncspace.domain.entities.InvoiceItems;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {BookingMapper.class, InvoiceMapper.class} )
public interface InvoiceItemsMapper {

    @Mapping(target = "bookingStatus", source = "booking.bookingStatus")
    @Mapping(target = "paymentStatus", source = "booking.paymentStatus")
    @Mapping(target = "notes", source = "booking.notes")
    InvoiceItemsResponseDto toDto(InvoiceItems invoiceItems);
}
