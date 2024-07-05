package com.dunamis.dunamisapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Permisos_Rol")
public class PermisosRol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "Permisos_PermisosID", referencedColumnName = "PermisosID")
    private Permisos permisos;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "Rol_idRol", referencedColumnName = "idRol")
    private Rol rol;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotNull Permisos getPermisos() {
        return permisos;
    }

    public void setPermisos(@NotNull Permisos permisos) {
        this.permisos = permisos;
    }

    public @NotNull Rol getRol() {
        return rol;
    }

    public void setRol(@NotNull Rol rol) {
        this.rol = rol;
    }
}
