package com.dunamis.dunamisapi.repository;

import com.dunamis.dunamisapi.model.Direccion;
import com.dunamis.dunamisapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    List<Usuario> findByPersona_IdPersona(String idPersona);
}
