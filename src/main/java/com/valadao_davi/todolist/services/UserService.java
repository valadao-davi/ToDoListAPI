package com.valadao_davi.todolist.services;

import com.valadao_davi.todolist.dto.UserCreateDTO;
import com.valadao_davi.todolist.dto.UserDTO;
import com.valadao_davi.todolist.entities.User;
import com.valadao_davi.todolist.exceptions.UserNotFoundException;
import com.valadao_davi.todolist.exceptions.UserRegisteredException;
import com.valadao_davi.todolist.projections.UserMinProjection;
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
    public List<UserMinProjection> getAllSafeUsers(){
        return userRepository.findAllBy().stream().toList();
    }

    @Transactional(readOnly = true)
    public UserMinProjection getMinUser(Long userId){
        return userRepository.findByUserId(userId).orElseThrow(UserNotFoundException::new);
    }

    private List<UserDTO> getAllUsers(){
        return userRepository.findAll().stream().map(UserDTO::new).toList();
    }


    @Transactional
    public void registerUser(UserCreateDTO userCreate){
        List<UserDTO> users = getAllUsers();
        UserDTO userDTO = new UserDTO(userCreate);
        if(users.contains(userDTO)){
            throw new UserRegisteredException();
        }
        System.out.println(userDTO);
        userRepository.saveAndFlush(new User(userDTO));

    }

}
