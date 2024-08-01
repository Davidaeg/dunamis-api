package com.dunamis.dunamisapi.repository;

import com.dunamis.dunamisapi.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DireccionRepository extends JpaRepository<Direccion, Integer> {
    List<Direccion> findByPersona_IdPersona(String idPersona);
}
