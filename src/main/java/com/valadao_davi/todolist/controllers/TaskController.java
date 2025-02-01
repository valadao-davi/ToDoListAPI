package com.valadao_davi.todolist.controllers;

import com.valadao_davi.todolist.dto.ActualDuration;
import com.valadao_davi.todolist.dto.TaskDTO;
import com.valadao_davi.todolist.dto.TaskUpdateDTO;
import com.valadao_davi.todolist.entities.Status;
import com.valadao_davi.todolist.projections.TaskProjection;
import com.valadao_davi.todolist.projections.UserMinProjection;
import com.valadao_davi.todolist.services.TaskService;
import com.valadao_davi.todolist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<TaskProjection> getAllTasks(){
        UserMinProjection user = userService.getProfile();
        return taskService.tasksByUserId(user.getUserId());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
       return ResponseEntity.ok(taskService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskDTO task){
        task.setStatus(Status.PENDING);
        UserMinProjection user = userService.getProfile();
        task.setUserId(user.getUserId());
        taskService.createTask(task);
        return ResponseEntity.ok("Task created");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task deleted");
    }


    @PutMapping(value = "/start/{id}")
    public ResponseEntity<TaskProjection> startTask(@PathVariable Long id){
        return ResponseEntity.ok(taskService.startTask(id));
    }

    @PutMapping(value = "/finish/{id}")
    public ResponseEntity<TaskProjection> finishTask(@PathVariable Long id){
        return ResponseEntity.ok(taskService.finishTask(id));
    }


    @PutMapping(value = "/stop/{id}")
    public ResponseEntity<TaskProjection> stopTask(@PathVariable Long id, @RequestBody ActualDuration actualDuration){
        return ResponseEntity.ok().body(taskService.stopTask(id, actualDuration.actualDuration()));
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<?> editTask(@PathVariable Long id,@RequestBody TaskUpdateDTO taskUpdate) {
        taskService.updateTask(taskUpdate, id);
        return ResponseEntity.ok().body("Updated task");
    }

}
