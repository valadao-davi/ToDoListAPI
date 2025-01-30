package com.valadao_davi.todolist.controllers;

import com.valadao_davi.todolist.dto.TaskDTO;
import com.valadao_davi.todolist.dto.TaskUpdateDTO;
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
       return ResponseEntity.ok(taskService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskDTO task){
        task.setStatus(Status.PENDING);
        taskService.createTask(task);
        return ResponseEntity.ok("Task created");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task deleted");
    }

    @PutMapping(value = "/start/{id}")
    public ResponseEntity<?> startTask(@PathVariable Long id){
        Double timeMinutes = taskService.startTask(id);
        if(timeMinutes != null && timeMinutes > 0){
            taskService.counterMinutes(id, timeMinutes);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Task interrupted.");
    }

    @PutMapping(value = "/stop")
    public ResponseEntity<?> stopTask(){
        taskService.stopTask();
        return ResponseEntity.ok().body("Task stopped");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> editTask(@PathVariable Long id,@RequestBody TaskUpdateDTO taskUpdate) {
        taskService.updateTask(taskUpdate, id);
        return ResponseEntity.ok().body("Updated task");
    }

}
