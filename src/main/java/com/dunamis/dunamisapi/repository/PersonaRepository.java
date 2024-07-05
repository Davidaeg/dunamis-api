package com.dunamis.dunamisapi.repository;

import com.dunamis.dunamisapi.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
}
