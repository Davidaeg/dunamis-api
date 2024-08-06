package com.dunamis.dunamisapi.dto;

public class UsuarioDTO {
    private int idUsuario;
    private String nombreUsuario;
    private String contrasenna;
    private String idRol;

    // Getters y Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public String getidRol() {
        return idRol;
    }

    public void setRolNombre(String idRol) {
        this.idRol = idRol;
    }
}

