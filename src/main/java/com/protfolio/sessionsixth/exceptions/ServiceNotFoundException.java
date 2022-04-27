package com.protfolio.sessionsixth.exceptions;


import lombok.Getter;

public class ServiceNotFoundException extends RuntimeException{

    @Getter
    private String message;

    public ServiceNotFoundException(String message){
        super();
        this.message = message;
    }



}
