package com.dunamis.dunamisapi.exception;

public class DireccionNotFoundException extends RuntimeException{
    public DireccionNotFoundException(int id){
        super("La direccion con el id " + id + " no se encuentra registrado en el sistema");
    }
}
