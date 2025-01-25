package com.valadao_davi.todolist.repositories;

import com.valadao_davi.todolist.entities.User;
import com.valadao_davi.todolist.projections.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<UserProjection> findAllBy();
}

