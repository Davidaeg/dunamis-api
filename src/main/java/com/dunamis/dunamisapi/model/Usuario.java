package com.dunamis.dunamisapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private int idUsuario;

    @NotNull
    @Column(name = "Nombre")
    private String nombre;

    @NotNull
    @Column(name = "Contrasenna")
    private String contrasenna;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "Rol_idRol", referencedColumnName = "idRol")
    private Rol rol;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "Persona_idPersona", referencedColumnName = "idPersona")
    private Persona persona;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public @NotNull String getNombre() {
        return nombre;
    }

    public void setNombre(@NotNull String nombre) {
        this.nombre = nombre;
    }

    public @NotNull String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(@NotNull String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public @NotNull Rol getRol() {
        return rol;
    }

    public void setRol(@NotNull Rol rol) {
        this.rol = rol;
    }

    public @NotNull Persona getPersona() {
        return persona;
    }

    public void setPersona(@NotNull Persona persona) {
        this.persona = persona;
    }
}