package com.devbp.syncspace.controllers;

import com.devbp.syncspace.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ap1/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


}
