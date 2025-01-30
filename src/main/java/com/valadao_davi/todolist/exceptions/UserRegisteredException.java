package com.valadao_davi.todolist.exceptions;

public class UserRegisteredException extends RuntimeException {

    public UserRegisteredException() { super("Email already registered."); }

    public UserRegisteredException(String message){
        super(message);
    }
}
