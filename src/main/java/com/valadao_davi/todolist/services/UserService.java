package com.valadao_davi.todolist.services;

import com.valadao_davi.todolist.dto.UserDTO;
import com.valadao_davi.todolist.projections.UserProjection;
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
    public List<UserProjection> getAllUsers(){
        return userRepository.findAllBy().stream().toList();
    }

}
