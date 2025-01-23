package com.valadao_davi.todolist.services;

import com.valadao_davi.todolist.dto.TaskDTO;
import com.valadao_davi.todolist.dto.TaskUpdateDTO;
import com.valadao_davi.todolist.entities.Status;
import com.valadao_davi.todolist.entities.Task;
import com.valadao_davi.todolist.repositories.TaskRepository;

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

    @Transactional(readOnly = true)
    public List<TaskDTO> getAllTasks(){
        return taskRepository.findAll().stream().map(TaskDTO::new).toList();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TaskDTO getById(Long id){
        return taskRepository.findById(id)
                .map(TaskDTO::new)
                .orElse(null);
    }

    @Transactional
    public boolean createTask(Task task){
        try{
            taskRepository.saveAndFlush(task);
            return true;
        } catch (Exception e){
            System.out.println("Error occured while trying to delete: " + e.getMessage());
            return false;
        }
    }

    @Transactional
    public int updateProgress(Long id, String status, Double globalDuration){
        return taskRepository.updateProgress(id, status, globalDuration);
    }

    @Transactional
    public boolean counterMinutes(Long id, Double timeDuration){
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
                return true;
            }
        } catch (InterruptedException e) {
            updateProgress(id, Status.PENDING.name(), globalDuration); // Update to PENDING on interrupt
            return false;
        } finally {
            startedTask = false;
        }
        return false;
    }

    @Transactional
    public Double startTask(Long id) {
        startedTask = true;
        TaskDTO task = getById(id);
        int rows = updateProgress(task.getIdTask(), Status.IN_PROGRESS.name(), task.getTimeTask());
        if(rows > 0){
            return task.getTimeTask();
        }
        return null;
    }

    @Transactional
    public boolean stopTask(){
        if(startedTask){
            System.out.println("started Task is true");
            Thread.currentThread().interrupt();
            startedTask = false;
            return true;
        }
        return false;

    }

    @Transactional
    public boolean deleteTask(Long id){
        try{
            if(taskRepository.existsById(id)){
                taskRepository.deleteById(id);
                return true;
            }else{
                return false;
            }

        } catch (Exception e){
            System.out.println("Error occured while trying to delete: " + e.getMessage());
            return false;
        }
    }

    @Transactional
    public boolean updateTask(TaskUpdateDTO taskUpdateDTO, Long id){
        Task task = taskRepository.findById(id).orElse(null);
        try{
            if(task != null){
                modelMapper.getConfiguration().setSkipNullEnabled(true);
                modelMapper.map(taskUpdateDTO, task);
                taskRepository.saveAndFlush(task);
                return true;
            }else{
                return false;
            }

        } catch (Exception e){
            System.out.println("Error occured while trying to delete: " + e.getMessage());
            return false;
        }
    }

}