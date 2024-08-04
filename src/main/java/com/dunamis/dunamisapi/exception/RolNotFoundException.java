package com.dunamis.dunamisapi.exception;

public class RolNotFoundException extends RuntimeException{
    public RolNotFoundException(int id){super("El rol con el id no existe " + id);}
}
