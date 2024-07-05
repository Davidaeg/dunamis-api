package com.dunamis.dunamisapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Reservacion")
public class Reservacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReservacion")
    private int idReservacion;

    @NotNull
    @Column(name = "Fecha_Inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @NotNull
    @Column(name = "Fecha_Fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "Automovil_Placa", referencedColumnName = "Placa")
    private Automovil automovil;

    @OneToMany(mappedBy = "reservacion")
    private Set<DetalleFactura> detalleFacturas;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "Cliente_idCliente", referencedColumnName = "idCliente")
    private Cliente cliente;

    public int getIdReservacion() {
        return idReservacion;
    }

    public void setIdReservacion(int idReservacion) {
        this.idReservacion = idReservacion;
    }

    public @NotNull Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(@NotNull Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public @NotNull Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(@NotNull Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public @NotNull Automovil getAutomovil() {
        return automovil;
    }

    public void setAutomovil(@NotNull Automovil automovil) {
        this.automovil = automovil;
    }

    public Set<DetalleFactura> getDetalleFacturas() {
        return detalleFacturas;
    }

    public void setDetalleFacturas(Set<DetalleFactura> detalleFacturas) {
        this.detalleFacturas = detalleFacturas;
    }

    public @NotNull Cliente getCliente() {
        return cliente;
    }

    public void setCliente(@NotNull Cliente cliente) {
        this.cliente = cliente;
    }
}
