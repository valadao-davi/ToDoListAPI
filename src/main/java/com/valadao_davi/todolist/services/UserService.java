package com.valadao_davi.todolist.services;

import com.valadao_davi.todolist.dto.UserDTO;
import com.valadao_davi.todolist.entities.User;
import com.valadao_davi.todolist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers(){
        List<UserDTO> userDTOS = userRepository.findAll().stream().map(UserDTO::new).toList();
        return userDTOS;
    }

}
