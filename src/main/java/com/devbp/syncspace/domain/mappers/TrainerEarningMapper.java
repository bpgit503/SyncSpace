package com.devbp.syncspace.domain.mappers;

import com.devbp.syncspace.domain.dtos.TrainerEarningsResponseDto;
import com.devbp.syncspace.domain.entities.TrainerEarnings;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TrainerEarningMapper {

    TrainerEarningsResponseDto toDto(TrainerEarnings trainerEarnings);
}
