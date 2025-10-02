package com.devbp.syncspace.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {BookingMapper.class, InvoiceMapper.class} )
public interface InvoiceItemsMapper {
}
