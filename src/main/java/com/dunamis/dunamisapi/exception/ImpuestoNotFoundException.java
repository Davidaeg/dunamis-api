package com.dunamis.dunamisapi.exception;

public class ImpuestoNotFoundException extends RuntimeException{
    public ImpuestoNotFoundException(int id){super("El impuesto con el id no existe " + id);}
}
