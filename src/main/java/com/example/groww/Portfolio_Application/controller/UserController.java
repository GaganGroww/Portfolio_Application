package com.example.groww.Portfolio_Application.controller;

import com.example.groww.Portfolio_Application.dto.UserDTO;
import com.example.groww.Portfolio_Application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("{userId}")
    private ResponseEntity<UserDTO> getUser(@PathVariable("userId") long userId){
        return new ResponseEntity<UserDTO> (userService.getUserInfo(userId), HttpStatus.OK);
    }

}

