package com.dunamis.dunamisapi.exception;

public class TipoAutomovilNotFoundException extends RuntimeException{
    public TipoAutomovilNotFoundException(int id){super("Could not found the Tipo Automovil with id " + id);}
}
