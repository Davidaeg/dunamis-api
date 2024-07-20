package com.dunamis.dunamisapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Cliente")
public class Cliente {
    @Id
    @Column(name = "idCliente")
    private String idCliente;

    @NotNull
    @Column(name = "Categoria_Licencia")
    private String categoriaLicencia;

    @NotNull
    @Column(name = "Fecha_Emision_Licencia")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaEmisionLicencia;

    @NotNull
    @Column(name = "Fecha_Vencimiento_Licencia")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaVencimientoLicencia;

    @NotNull
    @Column(name = "Estado")
    private String estado;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "Persona_idPersona", referencedColumnName = "idPersona")
    @JsonIgnore
    private Persona persona;

    @OneToMany(mappedBy = "cliente")
    private Set<Reservacion> reservaciones;

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public @NotNull String getCategoriaLicencia() {
        return categoriaLicencia;
    }

    public void setCategoriaLicencia(@NotNull  String categoriaLicencia) {
        this.categoriaLicencia = categoriaLicencia;
    }

    public @NotNull Date getFechaEmisionLicencia() {
        return fechaEmisionLicencia;
    }

    public void setFechaEmisionLicencia(@NotNull Date fechaEmisionLicencia) {
        this.fechaEmisionLicencia = fechaEmisionLicencia;
    }

    public @NotNull Date getFechaVencimientoLicencia() {
        return fechaVencimientoLicencia;
    }

    public void setFechaVencimientoLicencia(@NotNull Date fechaVencimientoLicencia) {
        this.fechaVencimientoLicencia = fechaVencimientoLicencia;
    }

    public @NotNull String getEstado() {
        return estado;
    }

    public void setEstado(@NotNull String estado) {
        this.estado = estado;
    }

    public @NotNull Persona getPersona() {
        return persona;
    }

    public void setPersona(@NotNull Persona persona) {
        this.persona = persona;
    }

    public Set<Reservacion> getReservaciones() {
        return reservaciones;
    }

    public void setReservaciones(Set<Reservacion> reservaciones) {
        this.reservaciones = reservaciones;
    }
}

