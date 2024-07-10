package com.dunamis.dunamisapi.exception;

public class PersonNotFoundException extends RuntimeException{
    public PersonNotFoundException(String id){
        super("La persona con ese id no se encuentra registrado en el sistema" + id);
    }
}
