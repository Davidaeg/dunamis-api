package com.dunamis.dunamisapi.exception;

public class PermisoNotFoundException extends RuntimeException{
    public PermisoNotFoundException(int id){super("El permiso con el id no existe " + id);}
}
