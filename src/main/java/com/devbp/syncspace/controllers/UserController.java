package com.devbp.syncspace.controllers;

import com.devbp.syncspace.domain.dtos.UserDto;
import com.devbp.syncspace.services.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("ap1/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> dummy = new ArrayList<>();
        //TODO
        return ResponseEntity.ok(dummy);
    }
}
