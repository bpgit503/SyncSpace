package com.devbp.syncspace.domain.mappers;

import com.devbp.syncspace.domain.dtos.ClassResponseDto;
import com.devbp.syncspace.domain.entities.Classes;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {ClassTypeMapper.class, TrainerMapper.class})
public interface ClassMapper {


    ClassResponseDto toDto(Classes classes);
}
