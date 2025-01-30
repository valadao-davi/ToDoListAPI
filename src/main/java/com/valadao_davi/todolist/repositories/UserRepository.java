package com.valadao_davi.todolist.repositories;
import com.valadao_davi.todolist.entities.User;
import com.valadao_davi.todolist.projections.UserMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByEmail(String email);

    List<UserMinProjection> findAllBy();

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<UserMinProjection> getUserProfile(String email);



}

