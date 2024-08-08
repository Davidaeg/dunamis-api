package com.dunamis.dunamisapi.dto;

import java.util.Date;

public class ReservacionDTO {
    private int idReservacion;
    private Date fechaInicio;
    private Date fechaFin;
    private int kmIniciales;
    private int kmFinales;
    private boolean reservacionActivo;
    private String autoPlaca;
    private String idCliente;

    public int getIdReservacion() {
        return idReservacion;
    }

    public void setIdReservacion(int idReservacion) {
        this.idReservacion = idReservacion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getKmIniciales() {
        return kmIniciales;
    }

    public void setKmIniciales(int kmIniciales) {
        this.kmIniciales = kmIniciales;
    }

    public boolean isReservacionActivo() {
        return reservacionActivo;
    }

    public void setReservacionActivo(boolean reservacionActivo) {
        this.reservacionActivo = reservacionActivo;
    }

    public int getKmFinales() {
        return kmFinales;
    }

    public void setKmFinales(int kmFinales) {
        this.kmFinales = kmFinales;
    }

    public String getAutoPlaca() {
        return autoPlaca;
    }

    public void setAutoPlaca(String autoPlaca) {
        this.autoPlaca = autoPlaca;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }
}
