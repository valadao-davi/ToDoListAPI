package com.valadao_davi.todolist.services;

import com.valadao_davi.todolist.dto.UserCreateDTO;
import com.valadao_davi.todolist.dto.UserLoginDTO;
import com.valadao_davi.todolist.exceptions.UserRegisteredException;
import com.valadao_davi.todolist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public void login(UserLoginDTO userLoginDTO){
        var userNamePassword = new UsernamePasswordAuthenticationToken(userLoginDTO.email(),userLoginDTO.password());
        var auth = authenticationManager.authenticate(userNamePassword);
    }

    public void register(UserCreateDTO userCreateDTO){
        if(userRepository.findByEmail(userCreateDTO.getEmail()) != null) throw new UserRegisteredException();

        String encryptedPassword = new BCryptPasswordEncoder().encode(userCreateDTO.getPassword());
        userCreateDTO.setPassword(encryptedPassword);
        userService.registerUser(userCreateDTO);
    }
}
