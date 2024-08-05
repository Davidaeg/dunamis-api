package com.dunamis.dunamisapi.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Persona")
public class Persona {
    @Id
    @NotNull
    @Column(name = "idPersona")
    private String idPersona;

    @NotNull
    @Column(name = "Nombre")
    private String nombre;

    @NotNull
    @Column(name = "Apellido1")
    private String apellido1;

    @NotNull
    @Column(name = "Apellido2")
    private String apellido2;

    @NotNull
    @Column(name = "Fecha_Nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @NotNull
    @Column(name = "Numero_Telefono")
    private String numeroTelefono;

    @NotNull
    @Column(name = "Numero_Celular")
    private String numeroCelular;

    @NotNull
    @Email
    @Column(name = "Email")
    private String email;

    @OneToMany(mappedBy = "persona")
    private Set<Usuario> usuarios;

    @OneToMany(mappedBy = "persona")
    private Set<Direccion> direcciones;

    @OneToMany(mappedBy = "persona")
    private Set<Cliente> clientes;

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public @NotNull String getNombre() {
        return nombre;
    }

    public void setNombre(@NotNull String nombre) {
        this.nombre = nombre;
    }

    public @NotNull String getApellido1() {
        return apellido1;
    }

    public void setApellido1(@NotNull String apellido1) {
        this.apellido1 = apellido1;
    }

    public @NotNull String getApellido2() {
        return apellido2;
    }

    public void setApellido2(@NotNull String apellido2) {
        this.apellido2 = apellido2;
    }

    public @NotNull Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(@NotNull Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public @NotNull String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(@NotNull String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public @NotNull String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(@NotNull String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public @NotNull @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotNull @Email String email) {
        this.email = email;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Set<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(Set<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public Set<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Set<Cliente> clientes) {
        this.clientes = clientes;
    }
}
