package com.dunamis.dunamisapi.controller;

import com.dunamis.dunamisapi.exception.PersonNotFoundException;
import com.dunamis.dunamisapi.model.Direccion;
import com.dunamis.dunamisapi.model.Persona;
import com.dunamis.dunamisapi.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.dunamis.dunamisapi.repository.DireccionRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:5173/", " http://localhost:3000/", "http://localhost:5174/"})

public class PersonController {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private DireccionRepository direccionRepository;

     @PostMapping("/persona")
     Persona newPersona(@RequestBody Persona newPersona) {
         if (newPersona.getIdPersona() == null || newPersona.getIdPersona().isEmpty()) {
             throw new IllegalArgumentException("El id de la persona no puede ser nulo o vacio");
         }
         if (newPersona.getNombre() == null || newPersona.getNombre().isEmpty()) {
             throw new IllegalArgumentException("El nombre de la persona no puede ser nulo o vacio");
         }
         if (newPersona.getApellido1() == null || newPersona.getApellido1().isEmpty()) {
             throw new IllegalArgumentException("El apellido paterno de la persona no puede ser nulo o vacio");
         }
         if (newPersona.getApellido2() == null || newPersona.getApellido2().isEmpty()) {
             throw new IllegalArgumentException("El apellido materno de la persona no puede ser nulo o vacio");
         }
         if (newPersona.getEmail() == null || newPersona.getEmail().isEmpty()) {
             throw new IllegalArgumentException("El email de la persona no puede ser nulo o vacio");
         }
         if (newPersona.getNumeroCelular() == null || newPersona.getNumeroCelular().isEmpty()) {
             throw new IllegalArgumentException("El numero de celular de la persona no puede ser nulo o vacio");
         }
         if (newPersona.getFechaNacimiento() == null) {
             throw new IllegalArgumentException("La fecha de nacimiento de la persona no puede ser nulo o vacio");
         }
         return personaRepository.save(newPersona);
     }



    @GetMapping("/personas")
    List<Persona> personasTodas(){return personaRepository.findAll();}

    @GetMapping("/persona/{id}")
    Persona obtenerPersonaPorId(@PathVariable String id){
        return personaRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    @PutMapping("/persona/{id}")
    Persona updatePerson(@RequestBody Persona newPerson, @PathVariable String id){
        return personaRepository.findById(id).map(persona ->{
            persona.setNombre(newPerson.getNombre());
            persona.setApellido1(newPerson.getApellido1());
            persona.setApellido2(newPerson.getApellido2());
            persona.setFechaNacimiento(newPerson.getFechaNacimiento());
            persona.setNumeroTelefono(newPerson.getNumeroTelefono());
            persona.setNumeroCelular(newPerson.getNumeroCelular());
            persona.setEmail(newPerson.getEmail());
            return personaRepository.save(persona);
        }).orElseThrow(() -> new PersonNotFoundException(id));
    }

    @DeleteMapping("/persona/{id}")
    String deletePersona(@PathVariable String id){
        if(!personaRepository.existsById(id)){
            throw new PersonNotFoundException(id);
        }
        List<Direccion> direcciones = direccionRepository.findByPersona_IdPersona(id);
        direccionRepository.deleteAll(direcciones);

        personaRepository.deleteById(id);
        return "La persona con el id " + id + " ha sido eliminada satisfactoriamente";
    }

}
