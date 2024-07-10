package com.dunamis.dunamisapi.exception;

public class AutomovilNotFoundException extends RuntimeException{
    public AutomovilNotFoundException(String id){
        super("Could not found the user with id "+ id);
    }
}
