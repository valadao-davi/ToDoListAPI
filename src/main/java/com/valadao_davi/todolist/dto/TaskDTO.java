package com.valadao_davi.todolist.dto;

import com.valadao_davi.todolist.entities.Priority;
import com.valadao_davi.todolist.entities.Status;
import com.valadao_davi.todolist.entities.Task;
import org.springframework.beans.BeanUtils;


public class TaskDTO {

    private Long idTask;
    private String nameTask;
    private Double durationTask;
    private Priority priority;
    private Status status;

    public TaskDTO(){}

    public TaskDTO(Task entity) {
        BeanUtils.copyProperties(entity, this);
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

}
