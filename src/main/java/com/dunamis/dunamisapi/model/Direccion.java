package com.dunamis.dunamisapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Direccion")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDireccion")
    private int idDireccion;

    @NotNull
    @Column(name = "Direccion")
    private String direccion;

    @NotNull
    @Column(name = "Provincia")
    private String provincia;

    @NotNull
    @Column(name = "Canton")
    private String canton;

    @NotNull
    @Column(name = "Distrito")
    private String distrito;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "Persona_idPersona", referencedColumnName = "idPersona")
    private Persona persona;

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public @NotNull String getDireccion() {
        return direccion;
    }

    public void setDireccion(@NotNull String direccion) {
        this.direccion = direccion;
    }

    public @NotNull String getProvincia() {
        return provincia;
    }

    public void setProvincia(@NotNull String provincia) {
        this.provincia = provincia;
    }

    public @NotNull String getCanton() {
        return canton;
    }

    public void setCanton(@NotNull String canton) {
        this.canton = canton;
    }

    public @NotNull String getDistrito() {
        return distrito;
    }

    public void setDistrito(@NotNull String distrito) {
        this.distrito = distrito;
    }

    public @NotNull Persona getPersona() {
        return persona;
    }

    public void setPersona(@NotNull Persona persona) {
        this.persona = persona;
    }
}
