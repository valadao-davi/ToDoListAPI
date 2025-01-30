package com.valadao_davi.todolist.dto;

import com.valadao_davi.todolist.entities.User;

import java.util.Objects;

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

    public UserDTO(UserCreateDTO entity){
        this.userName = entity.getUserName();
        this.email = entity.getPassword();
        this.password = entity.getPassword();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(getUserName(), userDTO.getUserName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getUserName());
    }
}
