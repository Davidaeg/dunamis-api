package com.dunamis.dunamisapi.exception;

public class FacturaNotFoundException extends RuntimeException{
    public FacturaNotFoundException(int id){super("Could not found the invoice with id" + id);}
}
