package com.devbp.syncspace.domain.mappers;

import com.devbp.syncspace.domain.dtos.ClassResponseDto;
import com.devbp.syncspace.domain.entities.Classes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {ClassTypeMapper.class, TrainerMapper.class})
public interface ClassMapper {

    @Mapping(target = "trainerId", source = "classes.trainer.id")
    @Mapping(target = "trainerName",
            expression = "java(classes.getTrainer().getUser().getFirstName() + \" \" + classes.getTrainer().getUser().getLastName())")
    @Mapping(target = "bio", source = "classes.trainer.bio")
    @Mapping(target = "isAvailable", source = "classes.trainer.available")
    ClassResponseDto toDto(Classes classes);
}
