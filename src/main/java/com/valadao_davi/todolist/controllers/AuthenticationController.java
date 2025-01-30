package com.valadao_davi.todolist.controllers;

import com.valadao_davi.todolist.dto.LoginResponseDTO;
import com.valadao_davi.todolist.dto.UserCreateDTO;
import com.valadao_davi.todolist.dto.UserLoginDTO;
import com.valadao_davi.todolist.services.AuthenticationService;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid UserLoginDTO userLoginDTO){
        String token = authenticationService.login(userLoginDTO);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserCreateDTO userCreateDTO){
        authenticationService.register(userCreateDTO);
        return ResponseEntity.ok().build();
    }
}
