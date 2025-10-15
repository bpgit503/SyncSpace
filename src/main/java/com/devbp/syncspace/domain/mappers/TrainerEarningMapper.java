package com.devbp.syncspace.domain.mappers;

import com.devbp.syncspace.domain.dtos.TrainerEarningsResponseDto;
import com.devbp.syncspace.domain.entities.TrainerEarnings;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
uses = {TrainerMapper.class, ClassMapper.class, UserMapper.class})
public interface TrainerEarningMapper {


    @Mapping(target = "id", source = "id")
    @Mapping(target = "trainerId", source = "trainer.id")
    @Mapping(target = "trainerName",  expression = "java(trainerEarnings.getTrainer().getUser().getFirstName() + \" \" + trainerEarnings.getTrainer().getUser().getLastName())")
    @Mapping(target = "clazzId", source = "clazz.id")
    @Mapping(target = "clazzName", source = "trainerEarnings.clazz.classType.className")
    TrainerEarningsResponseDto toDto(TrainerEarnings trainerEarnings);
}
