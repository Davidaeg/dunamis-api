package com.dunamis.dunamisapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "Segmento")
public class Segmento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSegmento")
    private int idSegmento;

    @NotNull
    @Column(name = "Nombre")
    private String nombre;

    @OneToMany(mappedBy = "segmento")
    private Set<Automovil> automoviles;

    public int getIdSegmento() {
        return idSegmento;
    }

    public void setIdSegmento(int idSegmento) {
        this.idSegmento = idSegmento;
    }

    public @NotNull String getNombre() {
        return nombre;
    }

    public void setNombre(@NotNull String nombre) {
        this.nombre = nombre;
    }

    public Set<Automovil> getAutomoviles() {
        return automoviles;
    }

    public void setAutomoviles(Set<Automovil> automoviles) {
        this.automoviles = automoviles;
    }
}
