package com.dunamis.dunamisapi.exception;

public class PersonNotFoundException extends RuntimeException{
    public PersonNotFoundException(String id){
        super("Could not found the user with id "+ id);
    }
}
