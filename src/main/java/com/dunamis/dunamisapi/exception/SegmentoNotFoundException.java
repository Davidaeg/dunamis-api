package com.dunamis.dunamisapi.exception;

public class SegmentoNotFoundException extends RuntimeException{
    public SegmentoNotFoundException(int id){super("Could not found the segmento with id " + id);}
}
