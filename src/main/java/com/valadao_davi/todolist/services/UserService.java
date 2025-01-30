package com.valadao_davi.todolist.services;

import com.valadao_davi.todolist.dto.UserCreateDTO;
import com.valadao_davi.todolist.dto.UserDTO;
import com.valadao_davi.todolist.entities.User;
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
        return userRepository.findByUserId(userId).orElse(null);
    }

    private List<UserDTO> getAllUsers(){
        return userRepository.findAll().stream().map(UserDTO::new).toList();
    }

    @Transactional
    private UserDTO getUserById(Long userId){
        return userRepository.findById(userId).map(UserDTO::new).orElse(null);
    }

    @Transactional
    public void registerUser(UserCreateDTO userCreate){
        List<UserDTO> users = getAllUsers();
        UserDTO userDTO = new UserDTO(userCreate);
        try{
            boolean contains = users.contains(userDTO);
            if(contains){
                throw new IllegalArgumentException("Email already registered.");
            }else{
                userRepository.saveAndFlush(new User(userDTO));
            }
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}
