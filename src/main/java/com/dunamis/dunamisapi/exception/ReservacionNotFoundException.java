package com.dunamis.dunamisapi.exception;

import com.dunamis.dunamisapi.model.Reservacion;

public class ReservacionNotFoundException extends RuntimeException{
    public ReservacionNotFoundException(int id){super("La reservacion con el id " + id + " no existe.");}
}
