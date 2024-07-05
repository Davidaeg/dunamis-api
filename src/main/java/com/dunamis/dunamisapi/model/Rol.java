package com.dunamis.dunamisapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "Rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRol")
    private int idRol;

    @NotNull
    @Column(name = "Nombre")
    private String nombre;

    @NotNull
    @Column(name = "Descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "rol")
    private Set<Usuario> usuarios;

    @OneToMany(mappedBy = "rol")
    private Set<PermisosRol> permisosRoles;

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public @NotNull String getNombre() {
        return nombre;
    }

    public void setNombre(@NotNull String nombre) {
        this.nombre = nombre;
    }

    public @NotNull String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NotNull String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Set<PermisosRol> getPermisosRoles() {
        return permisosRoles;
    }

    public void setPermisosRoles(Set<PermisosRol> permisosRoles) {
        this.permisosRoles = permisosRoles;
    }
}
