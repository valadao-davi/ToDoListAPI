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

//    private volatile boolean startedTask = false;
//    private Double globalDuration;

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
    public TaskProjection getById(Long id){
        return taskRepository.findByidTask(id)
                .orElseThrow(TaskNotFoundException::new);
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
    public TaskProjection updateProgress(Long id, String status, Double globalDuration){
        taskRepository.updateProgress(id, status, globalDuration);
        return getById(id);
    }

//    @Transactional
//    public void counterMinutes(Long id, Double timeDuration){
//        try {
//            this.globalDuration = timeDuration;
//            startedTask = true;
//            while (globalDuration > 0) {
//                if(!startedTask || Thread.currentThread().isInterrupted()){
//                    throw new InterruptedException();
//                }
//                Thread.sleep(1000);
//                globalDuration -= 0.0167;
//            }
//            if(globalDuration == 0){
//                updateProgress(id, Status.DONE.name(), timeDuration);
//            }
//        } catch (InterruptedException e) {
//            updateProgress(id, Status.PENDING.name(), globalDuration);
//        } finally {
//            startedTask = false;
//        }
//    }

    @Transactional
    public TaskProjection startTask(Long id) {
        TaskProjection task = getById(id);
        return updateProgress(task.getIdTask(), Status.IN_PROGRESS.name(), task.getDurationTask());
    }

    @Transactional
    public TaskProjection finishTask(Long id){
        TaskProjection task = getById(id);
        if(!task.getStatus().toString().equals("IN_PROGRESS")){
            throw new TaskNotFoundException("There's no task to finish");
        }else{
            return updateProgress(task.getIdTask(), Status.DONE.name(), task.getDurationTask());
        }
    }

    @Transactional
    public TaskProjection stopTask(Long id, Double actualDuration){
        TaskProjection task = getById(id);
        if(!task.getStatus().toString().equals("IN_PROGRESS")){
            throw new TaskNotFoundException("There's no task to stop");
        }else{
            Double difference = task.getDurationTask() - actualDuration;
            return updateProgress(task.getIdTask(), Status.PENDING.name(), difference);
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