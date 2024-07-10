package com.dunamis.dunamisapi.repository;

import com.dunamis.dunamisapi.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, String> {
    /*Persona findByIdPersona(String id);
    Persona findByEmailPersona(String email);
    Persona findByCelularPersona(String celular);
    Persona findByApellido1Persona(String apellido1);*/
    //Persona findByApellido2(String apellido2);
}
