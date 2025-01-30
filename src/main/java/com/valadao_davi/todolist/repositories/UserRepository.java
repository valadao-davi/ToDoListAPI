package com.valadao_davi.todolist.repositories;

import com.valadao_davi.todolist.dto.UserDTO;
import com.valadao_davi.todolist.entities.User;
import com.valadao_davi.todolist.projections.UserMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<UserMinProjection> findAllBy();

    Optional<UserMinProjection> findByUserId(Long userId);


}

