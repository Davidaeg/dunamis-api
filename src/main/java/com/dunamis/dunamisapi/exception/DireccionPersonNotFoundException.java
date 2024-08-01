package com.dunamis.dunamisapi.exception;

public class DireccionPersonNotFoundException extends RuntimeException{
    public DireccionPersonNotFoundException(String id){
        super("La direccion con el id " + id + " no se encuentra registrado en el sistema");
    }
}
