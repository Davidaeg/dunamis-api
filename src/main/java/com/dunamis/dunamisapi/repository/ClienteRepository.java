package com.dunamis.dunamisapi.repository;

import com.dunamis.dunamisapi.model.Cliente;
import com.dunamis.dunamisapi.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
    List<Cliente> findByPersona_IdPersona(String idPersona);
}
