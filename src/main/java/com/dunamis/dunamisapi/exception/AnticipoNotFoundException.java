package com.dunamis.dunamisapi.exception;

public class AnticipoNotFoundException extends RuntimeException{
    public AnticipoNotFoundException(int id){super("Could not found the anticipo with id " + id);}
}
