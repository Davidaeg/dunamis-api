package com.dunamis.dunamisapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Detalle_Factura")
public class DetalleFactura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetalle_Factura")
    private int idDetalleFactura;

    @NotNull
    @Column(name = "Subtotal")
    private double subtotal;

    @NotNull
    @Column(name = "PrecioKm_Automovil")
    private double precioKmAutomovil;

    @NotNull
    @Column(name = "Cantidad_Dias")
    private int cantidadDias;

    @NotNull
    @Column(name = "Cantidad_Km_Recorridos")
    private int cantidadKmRecorridos;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "Factura_idFactura", referencedColumnName = "idFactura")
    private Factura factura;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "Reservacion_idReservacion", referencedColumnName = "idReservacion")
    private Reservacion reservacion;

    public int getIdDetalleFactura() {
        return idDetalleFactura;
    }

    public void setIdDetalleFactura(int idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    @NotNull
    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(@NotNull double subtotal) {
        this.subtotal = subtotal;
    }

    @NotNull
    public double getPrecioKmAutomovil() {
        return precioKmAutomovil;
    }

    public void setPrecioKmAutomovil(@NotNull double precioKmAutomovil) {
        this.precioKmAutomovil = precioKmAutomovil;
    }

    @NotNull
    public int getCantidadDias() {
        return cantidadDias;
    }

    public void setCantidadDias(@NotNull int cantidadDias) {
        this.cantidadDias = cantidadDias;
    }

    @NotNull
    public double getCantidadKmRecorridos() {
        return cantidadKmRecorridos;
    }

    public void setCantidadKmRecorridos(@NotNull int cantidadKmRecorridos) {
        this.cantidadKmRecorridos = cantidadKmRecorridos;
    }

    public @NotNull Factura getFactura() {
        return factura;
    }

    public void setFactura(@NotNull Factura factura) {
        this.factura = factura;
    }

    public @NotNull Reservacion getReservacion() {
        return reservacion;
    }

    public void setReservacion(@NotNull Reservacion reservacion) {
        this.reservacion = reservacion;
    }
}