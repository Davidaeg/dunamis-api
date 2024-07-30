package com.dunamis.dunamisapi.exception;

public class TipoAutomovilNotFoundException extends RuntimeException{
    public TipoAutomovilNotFoundException(Long id){
        super("Could not found the user with id "+ id);
    }
}
