package com.valadao_davi.todolist.controllers;


import com.valadao_davi.todolist.projections.UserMinProjection;
import com.valadao_davi.todolist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public List<UserMinProjection> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<?> getMinUser(@PathVariable Long userId){
        UserMinProjection user = userService.getMinUser(userId);
        if(user != null){
            return ResponseEntity.ok(user);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}
