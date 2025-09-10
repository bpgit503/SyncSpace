package com.devbp.syncspace.controllers;

import com.devbp.syncspace.domain.CreateUserRequest;
import com.devbp.syncspace.domain.dtos.UserResponseDto;
import com.devbp.syncspace.domain.mappers.UserMapper;
import com.devbp.syncspace.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> userResponseDtos = userService.getAllUsers().stream()
                .map(userMapper::toDto)
                .toList();

        return ResponseEntity.ok(userResponseDtos);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {

        UserResponseDto userResponseDto = userMapper.toDto(userService.getUserById(id));

        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDto> getUserByEmail(@PathVariable String email) {

        UserResponseDto userResponseDto = userMapper.toDto(userService.getUserByEmail(email));

        return ResponseEntity.ok(userResponseDto);
    }


    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {

        UserResponseDto userResponseDto = userMapper.toDto(userService.createUser(createUserRequest));

        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);

    }


}
