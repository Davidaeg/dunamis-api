package com.dunamis.dunamisapi.exception;

public class AutomovilNotFoundException extends RuntimeException{
    public AutomovilNotFoundException(String placa){super("Could not found the Automovil with placa " + placa);}
}
