package com.dunamis.dunamisapi.repository;

import com.dunamis.dunamisapi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
}
