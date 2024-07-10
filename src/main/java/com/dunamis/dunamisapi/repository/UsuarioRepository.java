package com.dunamis.dunamisapi.repository;

import com.dunamis.dunamisapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByEmail(String name);
}
