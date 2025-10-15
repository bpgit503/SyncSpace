package com.devbp.syncspace.domain.mappers;

import com.devbp.syncspace.domain.dtos.ClassTypeResponseDto;
import com.devbp.syncspace.domain.entities.ClassType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClassTypeMapper {

    @Mapping(source = "active", target = "isActive")
    @Mapping(source = "groupClass", target = "isGroupClass")
    ClassTypeResponseDto toDto(ClassType classType);
}
