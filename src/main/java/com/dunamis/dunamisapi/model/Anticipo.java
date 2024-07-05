package com.dunamis.dunamisapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Anticipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAnticipo;

    @NotNull
    private double Valor;

    // Getters and setters

    public int getIdAnticipo() {
        return idAnticipo;
    }

    public void setIdAnticipo(int idAnticipo) {
        this.idAnticipo = idAnticipo;
    }

    public double getValor() {
        return Valor;
    }

    public void setValor(double valor) {
        Valor = valor;
    }
}
