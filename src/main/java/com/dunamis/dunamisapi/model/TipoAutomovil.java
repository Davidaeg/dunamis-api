package com.dunamis.dunamisapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Tipo_Automovil")
public class TipoAutomovil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "Tipo_idTipo", referencedColumnName = "idTipo")
    @JsonIgnore
    private Tipo tipo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "Automovil_Placa", referencedColumnName = "Placa")
    @JsonIgnore
    private Automovil automovil;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotNull Tipo getTipo() {
        return tipo;
    }

    public void setTipo(@NotNull Tipo tipo) {
        this.tipo = tipo;
    }

    public @NotNull Automovil getAutomovil() {
        return automovil;
    }

    public void setAutomovil(@NotNull Automovil automovil) {
        this.automovil = automovil;
    }
}
