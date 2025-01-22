package com.valadao_davi.todolist.repositories;

import com.valadao_davi.todolist.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Long> {


}
