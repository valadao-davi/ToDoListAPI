package com.valadao_davi.todolist.dto;

import com.valadao_davi.todolist.entities.Priority;
import com.valadao_davi.todolist.entities.Status;
import com.valadao_davi.todolist.entities.Task;


public class TaskDTO {

    private Long idTask;
    private Long userId;
    private String nameTask;
    private Double durationTask;
    private Priority priority;
    private Status status;

    public TaskDTO(){}

    public TaskDTO(Task task) {
        this.idTask = task.getIdTask();
        this.nameTask = task.getNameTask();
        this.durationTask = task.getDurationTask();
        this.priority = task.getPriority();
        this.status = task.getStatus();
        this.userId = task.getUser() != null ? task.getUser().getUserId() : null;
    }
    public Long getIdTask() {
        return idTask;
    }

    public void setIdTask(Long idTask) {
        this.idTask = idTask;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public Double getDurationTask() {
        return durationTask;
    }

    public void setDurationTask(Double durationTask) {
        this.durationTask = durationTask;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
