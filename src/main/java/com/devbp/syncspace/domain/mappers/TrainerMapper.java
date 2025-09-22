package com.devbp.syncspace.domain.mappers;

import com.devbp.syncspace.domain.dtos.TrainerResponseDto;
import com.devbp.syncspace.domain.entities.Trainer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TrainerMapper {

    @Mapping(source = "available", target = "isAvailable")
    TrainerResponseDto toDto(Trainer trainer);
}
