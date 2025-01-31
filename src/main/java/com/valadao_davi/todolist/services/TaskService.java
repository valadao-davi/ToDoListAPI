package com.valadao_davi.todolist.services;

import com.valadao_davi.todolist.dto.TaskDTO;
import com.valadao_davi.todolist.dto.TaskUpdateDTO;
import com.valadao_davi.todolist.entities.Status;
import com.valadao_davi.todolist.entities.Task;
import com.valadao_davi.todolist.entities.User;
import com.valadao_davi.todolist.exceptions.RegisterException;
import com.valadao_davi.todolist.exceptions.TaskNotFoundException;
import com.valadao_davi.todolist.exceptions.UserNotFoundException;
import com.valadao_davi.todolist.projections.TaskProjection;
import com.valadao_davi.todolist.repositories.TaskRepository;

import com.valadao_davi.todolist.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    private volatile boolean startedTask = false;
    private Double globalDuration;

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<TaskDTO> getAllTasks(){
        return taskRepository.findAll().stream().map(TaskDTO::new).toList();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<TaskProjection> tasksByUserId(Long userid){
        return taskRepository.tasksByUserId(userid);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TaskDTO getById(Long id){
        return taskRepository.findById(id)
                .map(TaskDTO::new)
                .orElse(null);
    }

    @Transactional(rollbackFor = Exception.class)
    public void createTask(TaskDTO taskDTO){
        if(taskDTO.getUserId() == null || taskDTO.getNameTask() == null || taskDTO.getDurationTask() == null || taskDTO.getPriority() == null ){
            throw new RegisterException();
        }
        User user = userRepository.findById(taskDTO.getUserId()).orElseThrow(UserNotFoundException::new);
        Task task = new Task();
        modelMapper.map(taskDTO, task);
        task.setUser(user);
        taskRepository.saveAndFlush(task);

    }

    @Transactional
    public int updateProgress(Long id, String status, Double globalDuration){
        return taskRepository.updateProgress(id, status, globalDuration);
    }

    @Transactional
    public void counterMinutes(Long id, Double timeDuration){
        try {
            this.globalDuration = timeDuration;
            startedTask = true;
            while (globalDuration > 0) {
                if(!startedTask || Thread.currentThread().isInterrupted()){
                    throw new InterruptedException();
                }
                Thread.sleep(1000);
                globalDuration -= 0.0167;
            }
            if(globalDuration == 0){
                updateProgress(id, Status.DONE.name(), timeDuration);
            }
        } catch (InterruptedException e) {
            updateProgress(id, Status.PENDING.name(), globalDuration);
        } finally {
            startedTask = false;
        }
    }

    // Returns time duration of a task
    @Transactional
    public Double startTask(Long id) {
        startedTask = true;
        TaskDTO task = getById(id);
        int rows = task != null ? updateProgress(task.getIdTask(), Status.IN_PROGRESS.name(), task.getDurationTask()) : 0;
        if(rows > 0){
            return task.getDurationTask();
        }
        return null;
    }

    @Transactional
    public void stopTask(){
        if(startedTask){
            Thread.currentThread().interrupt();
            startedTask = false;
        }else{
            throw new TaskNotFoundException("There's no task to stop");
        }

    }

    @Transactional
    public void deleteTask(Long id){
        if(taskRepository.existsById(id)){
            taskRepository.deleteById(id);
        }else {
            throw new TaskNotFoundException();
        }
    }

    @Transactional
    public void updateTask(TaskUpdateDTO taskUpdateDTO, Long id){
        Task task = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(taskUpdateDTO, task);
        taskRepository.saveAndFlush(task);
    }

}