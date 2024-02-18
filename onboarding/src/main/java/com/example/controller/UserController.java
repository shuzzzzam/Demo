package com.example.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.models.User;
import com.example.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class UserController {
    
    @Autowired
    UserService userService;


    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    

    @PostMapping("/user/add")
    public User postMethodName(@RequestBody User user) {
        user.setUserId(0);
        return userService.savUser(user);
    }
    
    @PutMapping("user/update/{id}")
    public User putMethodName(@PathVariable Long id, @RequestBody User User) {        
        return userService.updateUser(User, id);
    }

}
