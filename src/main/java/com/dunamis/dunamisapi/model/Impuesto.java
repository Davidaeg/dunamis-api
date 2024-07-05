package com.dunamis.dunamisapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Impuesto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idImpuesto;

    @NotNull
    private String Nombre;

    @NotNull
    private double Porcentaje;

    @NotNull
    private boolean Activo;

    public int getIdImpuesto() {
        return idImpuesto;
    }

    public void setIdImpuesto(int idImpuesto) {
        this.idImpuesto = idImpuesto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public double getPorcentaje() {
        return Porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        Porcentaje = porcentaje;
    }

    public boolean isActivo() {
        return Activo;
    }

    public void setActivo(boolean activo) {
        Activo = activo;
    }
}