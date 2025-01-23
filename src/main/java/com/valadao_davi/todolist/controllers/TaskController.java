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
       TaskDTO taskDTO = taskService.getById(id);
       if(taskDTO != null){
           return ResponseEntity.ok(taskDTO);
       }else{
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
       }
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task task){
        task.setStatus(Status.PENDING);
        boolean state = taskService.createTask(task);
        if(state){
            return ResponseEntity.ok("Task created");
        }else{
            return ResponseEntity.status(HttpStatus.valueOf(400)).body("Error while creating task");
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        boolean state = taskService.deleteTask(id);
        if(state){
            return ResponseEntity.ok("Task deleted");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Error while deleting task");
        }
    }

    @PutMapping(value = "/start/{id}")
    public ResponseEntity<?> startTask(@PathVariable Long id){
        Double timeMinutes = taskService.startTask(id);
        if(timeMinutes != null){
            boolean typeFinish = taskService.counterMinutes(id, timeMinutes);
            if(typeFinish){
                return ResponseEntity.ok().body("Done task.");
            }else{
                return  ResponseEntity.ok().body("Task interrupted.");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found.");
    }

    @PutMapping(value = "/stop")
    public ResponseEntity<?> stopTask(){
        boolean state = taskService.stopTask();
        if(state){
            return ResponseEntity.ok().body("Task stopped");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("There's no task to stop.");

        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> editTask(@PathVariable Long id,@RequestBody TaskUpdateDTO taskUpdate){
        boolean state = taskService.updateTask(taskUpdate, id);
        if(state){
            return ResponseEntity.ok().body("Updated task");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error trying to update.");

        }
    }

}
