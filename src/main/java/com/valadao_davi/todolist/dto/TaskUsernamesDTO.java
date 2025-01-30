package com.valadao_davi.todolist.dto;


public record TaskUsernamesDTO(Long taskId, String nameTask, Double durationTask, String priority, String status, String userName) {
}
