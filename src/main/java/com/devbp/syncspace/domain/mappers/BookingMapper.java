package com.devbp.syncspace.domain.mappers;

import com.devbp.syncspace.domain.dtos.BookingResponseDto;
import com.devbp.syncspace.domain.entities.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
uses = {UserMapper.class, ClassMapper.class})
public interface BookingMapper {

    BookingResponseDto toDto(Booking booking);
}
