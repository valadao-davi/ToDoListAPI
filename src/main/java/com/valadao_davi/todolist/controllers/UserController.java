package com.valadao_davi.todolist.controllers;


import com.valadao_davi.todolist.dto.UserCreateDTO;
import com.valadao_davi.todolist.exceptions.UserNotFoundException;
import com.valadao_davi.todolist.exceptions.UserRegisteredException;
import com.valadao_davi.todolist.projections.UserMinProjection;
import com.valadao_davi.todolist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public List<UserMinProjection> getAllUsers() {
        return userService.getAllSafeUsers();
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<?> getMinUser(@PathVariable Long userId){
        UserMinProjection user = userService.getMinUser(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/user")
    public ResponseEntity<String> registerUser(@RequestBody UserCreateDTO userCreateDTO){
            userService.registerUser(userCreateDTO);
            return ResponseEntity.ok("User created.");
    }

    @PutMapping(value = "/user/{userId}")
    public ResponseEntity<String> editUser(@RequestBody UserCreateDTO userCreateDTO, @PathVariable Long userId){
        userService.editUser(userCreateDTO, userId);
        return ResponseEntity.ok("User edited.");
    }

    @DeleteMapping(value = "/user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted.");
    }
}
