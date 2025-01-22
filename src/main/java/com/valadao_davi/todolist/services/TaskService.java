package com.valadao_davi.todolist.services;

import com.valadao_davi.todolist.dto.TaskDTO;
import com.valadao_davi.todolist.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Transactional(readOnly = true)

    public List<TaskDTO> getAllTasks(){
        return taskRepository.findAll().stream().map(TaskDTO::new).toList();
    }

    public TaskDTO getById(Long id){
        return new TaskDTO(taskRepository.findById(id).get());
    }

}
