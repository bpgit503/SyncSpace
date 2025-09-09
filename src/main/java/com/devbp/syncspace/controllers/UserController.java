package com.devbp.syncspace.controllers;

import com.devbp.syncspace.domain.dtos.UserDto;
import com.devbp.syncspace.domain.mappers.UserMapper;
import com.devbp.syncspace.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtos = userService.getAllUsers().stream()
                .map(userMapper::toDto)
                .toList();

        return ResponseEntity.ok(userDtos);
    }
}
