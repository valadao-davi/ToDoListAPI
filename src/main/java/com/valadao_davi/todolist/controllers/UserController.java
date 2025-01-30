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
@RequestMapping(value = "/")
public class UserController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public List<UserMinProjection> getAllUsers() {
        return userService.getAllSafeUsers();
    }


    @GetMapping(value = "/profile")
    public ResponseEntity<?> getProfile(){
        UserMinProjection user = userService.getProfile();
        return ResponseEntity.ok(user);
    }

    @PutMapping(value = "/profile")
    public ResponseEntity<String> editUser(@RequestBody UserCreateDTO userCreateDTO){
        UserMinProjection user = userService.getProfile();
        userService.editUser(userCreateDTO, user.getUserId());
        return ResponseEntity.ok("User edited.");
    }

    @DeleteMapping(value = "/user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted.");
    }
}
