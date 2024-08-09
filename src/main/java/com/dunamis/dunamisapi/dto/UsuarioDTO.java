package com.dunamis.dunamisapi.dto;

public class UsuarioDTO {
    private int idUsuario;
    private String emailUsuario;
    private String contrasenna;
    private String idRol;

    // Getters y Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public String getIdRol() {
        return idRol;
    }

    public void setRolNombre(String idRol) {
        this.idRol = idRol;
    }
}


