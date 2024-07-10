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
    @Column(name = "Color")
    private String color;

    @NotNull
    @Column(name = "Estilo")
    private String estilo;

    @NotNull
    @Column(name = "Carroceria")
    private String carroceria;

    @NotNull
    @Column(name = "Combustible")
    private String combustible;

    @NotNull
    @Column(name = "Cabina")
    private String cabina;

    @NotNull
    @Column(name = "Traccion")
    private String traccion;

    @NotNull
    @Column(name = "Transmision")
    private String Transmision;

    @NotNull
    @Column(name = "Costo")
    private double costo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "Segmento_id_Segmento", referencedColumnName = "idSegmento")
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
    public String getTraccion() {
        return traccion;
    }

    public void setTraccion(String traccion) {
        this.traccion = traccion;
    }
    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }
    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }
    public String getCabina() {
        return cabina;
    }

    public void setCabina(String cabina) {
        this.cabina = cabina;
    }

    public String getCarroceria() {
        return carroceria;
    }

    public void setCarroceria(String carroceria) {
        this.carroceria = carroceria;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTransmision() {
        return Transmision;
    }

    public void setTransmision(String transmision) {
        this.Transmision = transmision;
    }

    public  Segmento getSegmento() {
        return segmento;
    }

    public void setSegmento( Segmento segmento) {
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
