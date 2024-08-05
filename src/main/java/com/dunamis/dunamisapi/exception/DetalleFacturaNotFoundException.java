package com.dunamis.dunamisapi.exception;

public class DetalleFacturaNotFoundException extends RuntimeException{
    public DetalleFacturaNotFoundException(int id){super("El detalle de factura con el id no existe " + id);}
}
