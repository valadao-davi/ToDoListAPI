package com.valadao_davi.todolist.dto;

import com.valadao_davi.todolist.entities.Priority;
import com.valadao_davi.todolist.entities.Status;

public record TaskUsernamesDTO(Long taskId, String nameTask, Double durationTask, Priority priority, Status status, String userName) {
}
