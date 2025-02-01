package com.valadao_davi.todolist.controllers;
import com.valadao_davi.todolist.dto.UserCreateDTO;
import com.valadao_davi.todolist.entities.User;
import com.valadao_davi.todolist.infra.TokenService;
import com.valadao_davi.todolist.projections.UserMinProjection;
import com.valadao_davi.todolist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/profile")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getProfile(){
        UserMinProjection user = userService.getProfile();
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<String> editUser(@RequestBody UserCreateDTO userCreateDTO){
        UserMinProjection user = userService.getProfile();
        userService.editUser(userCreateDTO, user.getUserId());
        return ResponseEntity.ok("User edited.");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(){
        UserMinProjection user = userService.getProfile();
        userService.deleteUser(user.getUserId());
        return ResponseEntity.ok("User deleted.");
    }
}
