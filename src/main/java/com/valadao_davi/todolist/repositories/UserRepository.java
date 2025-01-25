package com.valadao_davi.todolist.repositories;

import com.valadao_davi.todolist.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

