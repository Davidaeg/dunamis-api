package com.dunamis.dunamisapi.exception;

public class TipoNotFoundException extends RuntimeException{
    public TipoNotFoundException(int id){super("Could not found tipo with id " + id);}
}
