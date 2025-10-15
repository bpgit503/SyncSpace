package com.devbp.syncspace.domain.mappers;

import com.devbp.syncspace.domain.dtos.TrainerResponseDto;
import com.devbp.syncspace.domain.entities.Trainer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TrainerMapper {

    @Mapping(target = "userId", source = "trainer.user.id")
    @Mapping(target = "email", source = "trainer.user.email")
    @Mapping(target = "phoneNumber", source = "trainer.user.phoneNumber")
    @Mapping(target = "trainerName", expression = "java(trainer.getUser().getFirstName() + \" \" + trainer.getUser().getLastName())")
    @Mapping(target = "isAvailable",source = "available")
    @Mapping(target = "userType" ,source = "trainer.user.userType" )
    TrainerResponseDto toDto(Trainer trainer);
}
