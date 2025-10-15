package com.devbp.syncspace.domain.mappers;

import com.devbp.syncspace.domain.dtos.UpdateUserRequest;
import com.devbp.syncspace.domain.dtos.UpdateUserRequestDto;
import com.devbp.syncspace.domain.dtos.UserResponseDto;
import com.devbp.syncspace.domain.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "status", source = "status")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    UserResponseDto toDto(User user);

    UpdateUserRequest toUpdateUserRequest(UpdateUserRequestDto updateUserRequestDto);

}
