package com.dunamis.dunamisapi.exception;

public class ReservacionNotFoundException extends RuntimeException{
    public ReservacionNotFoundException(Long id){
        super("Could not found the user with id "+ id);
    }
}
