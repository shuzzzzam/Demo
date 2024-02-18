package com.example.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.models.User;
import com.example.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class UserController {
    
    @Autowired
    UserService userService;


    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    

}
