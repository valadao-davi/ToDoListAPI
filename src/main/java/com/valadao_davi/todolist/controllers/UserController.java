package com.valadao_davi.todolist.controllers;

import com.valadao_davi.todolist.dto.UserDTO;
import com.valadao_davi.todolist.projections.UserProjection;
import com.valadao_davi.todolist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public List<UserProjection> getAllUsers() {
        return userService.getAllUsers();
    }
}
