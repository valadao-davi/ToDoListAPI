package com.valadao_davi.todolist.projections;


import com.valadao_davi.todolist.entities.Priority;
import com.valadao_davi.todolist.entities.Status;

public interface TaskProjection {

    Long getIdTask();
    String getNameTask();
    Double getDurationTask();
    Priority getPriority();
    Status getStatus();


}
