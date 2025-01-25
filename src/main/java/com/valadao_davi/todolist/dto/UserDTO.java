package com.valadao_davi.todolist.dto;

import com.valadao_davi.todolist.entities.User;

public class UserDTO {

    private Long userId;
    private String userName;
    private String email;
    private String password;

    public UserDTO(User entity) {
        this.userId = entity.getUserId();
        this.userName = entity.getUserName();
        this.password = entity.getPassword();
        this.email = entity.getEmail();
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
