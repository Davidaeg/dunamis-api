package com.dunamis.dunamisapi.exception;

public class ClienteNotFoundException extends RuntimeException{

    public ClienteNotFoundException(String id){
        super("El cliente con ese id no se encuentra registrado en el sistema" + id);
    }
}
