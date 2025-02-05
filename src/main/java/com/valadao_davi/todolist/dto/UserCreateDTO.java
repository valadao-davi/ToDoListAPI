package com.valadao_davi.todolist.dto;


import jakarta.validation.constraints.NotBlank;

public class UserCreateDTO {

    private String userName;
    private String email;
    private String password;

    public UserCreateDTO() {

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
