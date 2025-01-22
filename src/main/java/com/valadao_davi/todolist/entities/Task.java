package com.valadao_davi.todolist.entities;

import jakarta.persistence.*;

import java.time.Duration;
import java.util.Objects;

@Entity
@Table(name = "table_task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTask;
    private String nameTask;
    private Integer timeTask;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Task(){}

    public Task(Long idTask, String nameTask, Integer timeTask, Priority priority) {
        this.idTask = idTask;
        this.nameTask = nameTask;
        this.timeTask = timeTask;
        this.priority = priority;
        status = Status.PENDING;
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

    public Integer getTimeTask() {
        return timeTask;
    }

    public void setTimeTask(Integer timeTask) {
        this.timeTask = timeTask;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(getIdTask(), task.getIdTask());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getIdTask());
    }
}
