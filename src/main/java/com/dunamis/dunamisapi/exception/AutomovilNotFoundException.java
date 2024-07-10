package com.dunamis.dunamisapi.exception;

public class AutomovilNotFoundException extends RuntimeException{
    public AutomovilNotFoundException(Long id){
        super("Could not found the user with id "+ id);
    }
}
