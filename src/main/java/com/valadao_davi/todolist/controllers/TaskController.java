package com.valadao_davi.todolist.controllers;

import com.valadao_davi.todolist.dto.TaskDTO;
import com.valadao_davi.todolist.entities.Status;
import com.valadao_davi.todolist.entities.Task;
import com.valadao_davi.todolist.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<TaskDTO> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
       TaskDTO taskDTO = taskService.getById(id);
       if(taskDTO != null){
           return ResponseEntity.ok(taskDTO);
       }else{
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
       }
    }

    @PostMapping
    public void createTask(@RequestBody Task task){
        task.setStatus(Status.PENDING);
        taskService.createTask(task);
    }
}
