package com.dunamis.dunamisapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "Automovil")
public class Automovil {
    @Id
    @Column(name = "Placa")
    private String placa;

    @NotNull
    @Column(name = "Marca")
    private String marca;

    @NotNull
    @Column(name = "Modelo")
    private String modelo;

    @NotNull
    @Column(name = "Anno")
    private int anno;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "Segmento_idSegmento", referencedColumnName = "idSegmento")
    private Segmento segmento;

    @OneToMany(mappedBy = "automovil")
    private Set<Reservacion> reservaciones;

    @OneToMany(mappedBy = "automovil")
    private Set<TipoAutomovil> tipoAutomoviles;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public @NotNull String getMarca() {
        return marca;
    }

    public void setMarca(@NotNull String marca) {
        this.marca = marca;
    }

    public @NotNull String getModelo() {
        return modelo;
    }

    public void setModelo(@NotNull String modelo) {
        this.modelo = modelo;
    }

    @NotNull
    public int getAnno() {
        return anno;
    }

    public void setAnno(@NotNull int anno) {
        this.anno = anno;
    }

    public @NotNull Segmento getSegmento() {
        return segmento;
    }

    public void setSegmento(@NotNull Segmento segmento) {
        this.segmento = segmento;
    }

    public Set<Reservacion> getReservaciones() {
        return reservaciones;
    }

    public void setReservaciones(Set<Reservacion> reservaciones) {
        this.reservaciones = reservaciones;
    }

    public Set<TipoAutomovil> getTipoAutomoviles() {
        return tipoAutomoviles;
    }

    public void setTipoAutomoviles(Set<TipoAutomovil> tipoAutomoviles) {
        this.tipoAutomoviles = tipoAutomoviles;
    }
}
