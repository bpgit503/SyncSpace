package com.devbp.syncspace.domain.mappers;

import com.devbp.syncspace.domain.UpdateUserRequest;
import com.devbp.syncspace.domain.dtos.UpdateUserRequestDto;
import com.devbp.syncspace.domain.dtos.UserResponseDto;
import com.devbp.syncspace.domain.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserResponseDto toDto(User user);

    UpdateUserRequest toDto(UpdateUserRequestDto updateUserRequestDto);

}
