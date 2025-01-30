package com.valadao_davi.todolist.exceptions;

public class JWTErrorException extends RuntimeException{

    public JWTErrorException(){
        super("Ocurred an error in JWT Process.");
    }

    public JWTErrorException(String message){
        super(message);
    }
}
