package com.devbp.syncspace.domain.mappers;

import com.devbp.syncspace.domain.dtos.BookingResponseDto;
import com.devbp.syncspace.domain.entities.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
uses = {UserMapper.class, ClassMapper.class})
public interface BookingMapper {

    @Mapping(target = "clientId", source = "booking.client.id")
    @Mapping(target = "email", source = "booking.client.email")
    @Mapping(target = "firstName", source = "booking.client.firstName")
    @Mapping(target = "lastName", source = "booking.client.lastName")
    @Mapping(target = "phoneNumber", source = "booking.client.phoneNumber")
    @Mapping(target = "classId", source = "booking.clazz.id")
    @Mapping(target = "scheduledDate", source = "booking.clazz.scheduledDate")
    @Mapping(target = "startTime", source = "booking.clazz.startTime")
    @Mapping(target = "endTime", source = "booking.clazz.endTime")
    @Mapping(target = "maxCapacity", source = "booking.clazz.maxCapacity")
    BookingResponseDto toDto(Booking booking);
}
