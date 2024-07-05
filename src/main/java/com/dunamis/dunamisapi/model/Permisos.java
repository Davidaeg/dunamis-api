package com.dunamis.dunamisapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "Permisos")
public class Permisos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PermisosID")
    private int permisosID;

    @NotNull
    @Column(name = "Descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "permisos")
    private Set<PermisosRol> permisosRoles;

    public int getPermisosID() {
        return permisosID;
    }

    public void setPermisosID(int permisosID) {
        this.permisosID = permisosID;
    }

    public @NotNull String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NotNull String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<PermisosRol> getPermisosRoles() {
        return permisosRoles;
    }

    public void setPermisosRoles(Set<PermisosRol> permisosRoles) {
        this.permisosRoles = permisosRoles;
    }
}
