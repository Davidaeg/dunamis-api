package com.dunamis.dunamisapi.dto;

public class DetalleFacturaDTO {
    private int idDetalleFactura;
    private double subtotal;
    private double precioKmAutomovil;
    private int cantidadDias;
    private int cantidadKmRecorridos;
    private String facturaFecha;
    private String reservacionId;

    public int getIdDetalleFactura() {
        return idDetalleFactura;
    }

    public void setIdDetalleFactura(int idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getPrecioKmAutomovil() {
        return precioKmAutomovil;
    }

    public void setPrecioKmAutomovil(double precioKmAutomovil) {
        this.precioKmAutomovil = precioKmAutomovil;
    }

    public int getCantidadDias() {
        return cantidadDias;
    }

    public void setCantidadDias(int cantidadDias) {
        this.cantidadDias = cantidadDias;
    }

    public String getFacturaFecha() {
        return facturaFecha;
    }

    public void setFacturaFecha(String facturaFecha) {
        this.facturaFecha = facturaFecha;
    }

    public int getCantidadKmRecorridos() {
        return cantidadKmRecorridos;
    }

    public void setCantidadKmRecorridos(int cantidadKmRecorridos) {
        this.cantidadKmRecorridos = cantidadKmRecorridos;
    }

    public String getReservacionId() {
        return reservacionId;
    }

    public void setReservacionId(String reservacionId) {
        this.reservacionId = reservacionId;
    }
}
