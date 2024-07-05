package com.dunamis.dunamisapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "Tipo")
public class Tipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTipo")
    private int idTipo;

    @NotNull
    @Column(name = "Nombre")
    private String nombre;

    @OneToMany(mappedBy = "tipo")
    private Set<TipoAutomovil> tipoAutomoviles;

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public @NotNull String getNombre() {
        return nombre;
    }

    public void setNombre(@NotNull String nombre) {
        this.nombre = nombre;
    }

    public Set<TipoAutomovil> getTipoAutomoviles() {
        return tipoAutomoviles;
    }

    public void setTipoAutomoviles(Set<TipoAutomovil> tipoAutomoviles) {
        this.tipoAutomoviles = tipoAutomoviles;
    }
}
