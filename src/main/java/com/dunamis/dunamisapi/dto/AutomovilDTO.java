package com.dunamis.dunamisapi.dto;

public class AutomovilDTO {
    private String placa;
    private String marca;
    private String modelo;
    private int anno;
    private String color;
    private String estilo;
    private String carroceria;
    private String combustible;
    private String cabina;
    private String traccion;
    private String transmision;
    private double costo;
    private boolean automovilActivo;
    private String segmentoNombre;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getCarroceria() {
        return carroceria;
    }

    public void setCarroceria(String carroceria) {
        this.carroceria = carroceria;
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

    public String getTraccion() {
        return traccion;
    }

    public void setTraccion(String traccion) {
        this.traccion = traccion;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public boolean isAutomovilActivo() {
        return automovilActivo;
    }

    public void setAutomovilActivo(boolean automovilActivo) {
        this.automovilActivo = automovilActivo;
    }

    public String getSegmentoNombre() {
        return segmentoNombre;
    }

    public void setSegmentoNombre(String segmentoNombre) {
        this.segmentoNombre = segmentoNombre;
    }
}
