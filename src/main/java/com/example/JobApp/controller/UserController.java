package com.example.JobApp.controller;

import com.example.JobApp.model.User;
import com.example.JobApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResponseEntity<User> register(@RequestBody User user){

        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("getuser/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") Integer userId){

        return new ResponseEntity<>(userService.getUser(userId),HttpStatus.OK);
    }

    @PutMapping("updateuser")
    public User updateUser(){
        return null;
    }

    @DeleteMapping("deleteuser")
    public User deleteUser(){
        return null;
    }

}
