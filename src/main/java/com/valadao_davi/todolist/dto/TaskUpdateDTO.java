package com.valadao_davi.todolist.dto;

import com.valadao_davi.todolist.entities.Priority;
import com.valadao_davi.todolist.entities.Task;

public class TaskUpdateDTO {

    private String nameTask;
    private Priority priority;
    private Double durationTask;

    public TaskUpdateDTO(){}

    public TaskUpdateDTO(Task entity) {
        nameTask = entity.getNameTask();
        priority = entity.getPriority();
        durationTask = entity.getDurationTask();
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Double getDurationTask() {
        return durationTask;
    }

    public void setDurationTask(Double durationTask) {
        this.durationTask = durationTask;
    }
}
