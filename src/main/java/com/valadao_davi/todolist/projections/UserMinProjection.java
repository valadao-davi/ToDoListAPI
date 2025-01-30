package com.valadao_davi.todolist.projections;

import com.valadao_davi.todolist.entities.Task;

import java.util.List;

public interface UserMinProjection {
    Long getUserId();
    String getUserName();
    String getEmail();
}
