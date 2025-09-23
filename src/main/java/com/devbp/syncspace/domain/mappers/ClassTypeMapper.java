package com.devbp.syncspace.domain.mappers;

import com.devbp.syncspace.domain.dtos.ClassTypeResponseDto;
import com.devbp.syncspace.domain.dtos.CreateClassTypeRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClassTypeMapper {

    ClassTypeResponseDto toDto(CreateClassTypeRequest createClassTypeRequest);
}
