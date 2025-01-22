package com.valadao_davi.todolist.services;

import com.valadao_davi.todolist.dto.TaskDTO;
import com.valadao_davi.todolist.entities.Status;
import com.valadao_davi.todolist.entities.Task;
import com.valadao_davi.todolist.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    private boolean startedTask;
    private Integer globalDuration;
    private Thread taskThread;


    @Autowired
    private TaskRepository taskRepository;

    @Transactional(readOnly = true)
    public List<TaskDTO> getAllTasks(){
        return taskRepository.findAll().stream().map(TaskDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public TaskDTO getById(Long id){
        return taskRepository.findById(id)
                .map(TaskDTO::new)
                .orElse(null);
    }

    @Transactional
    public void createTask(Task task){
        taskRepository.saveAndFlush(task);
    }

    @Transactional
    public void startTask(Long id) {
        globalDuration = getById(id).getTimeTask();
        taskRepository.updateProgress(id, "IN_PROGRESS", globalDuration);
        startedTask = true;
        taskThread = new Thread(() -> {
            try {
                while (globalDuration > 0 ) {
                    if (!startedTask || Thread.currentThread().isInterrupted()) {
                        throw new InterruptedException();
                    }
                    Thread.sleep(1000);
                    globalDuration -= 1;
                }
            } catch (InterruptedException e) {
                taskRepository.updateProgress(id,"PENDING", globalDuration);
            } finally {
                startedTask = false;
            }
        });

        taskThread.start();

        try {
            taskThread.join();
            taskRepository.updateProgress(id, "DONE", null);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }

    @Transactional
    public void stopTask(Long id){
        if(startedTask){
            startedTask = false;
            taskRepository.updateProgress(id, "PENDING", globalDuration);
            taskThread.interrupt();
        }
    }

}
