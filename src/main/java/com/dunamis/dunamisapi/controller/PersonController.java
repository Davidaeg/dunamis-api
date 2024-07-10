package com.dunamis.dunamisapi.controller;


import com.dunamis.dunamisapi.exception.PersonNotFoundException;
import com.dunamis.dunamisapi.model.Persona;
import com.dunamis.dunamisapi.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:5173/", " http://localhost:3000/", "http://localhost:5174/"})
public class PersonController {

    @Autowired
    private PersonaRepository personaRepository;

    @PostMapping("/persona")
    Persona newPersona(@RequestBody Persona newPersona) {
        if (newPersona.getIdPersona() == null || newPersona.getIdPersona().isEmpty()) {
            throw new IllegalArgumentException("idPersona cannot be null or empty");
        }
        return personaRepository.save(newPersona);
    }

    @GetMapping("/personas")
    List<Persona> getAllPeople() {
        return personaRepository.findAll();
    }

    @GetMapping("/persona/{id}")
    Persona getPersonById(@PathVariable String id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @PutMapping("/persona/{id}")
    Persona updatePerson(@RequestBody Persona newPersona, @PathVariable String id) {
        return personaRepository.findById(id)
                .map(person -> {
                    person.setNombre(newPersona.getNombre());
                    person.setApellido1(newPersona.getApellido1());
                    person.setApellido2(newPersona.getApellido2());
                    person.setEmail(newPersona.getEmail());
                    person.setFechaNacimiento(newPersona.getFechaNacimiento());
                    person.setNumeroCelular(newPersona.getNumeroCelular());
                    person.setNumeroTelefono(newPersona.getNumeroTelefono());
                    return personaRepository.save(person);
                }).orElseThrow(() -> new PersonNotFoundException(id));
    }

    @DeleteMapping("/persona/{id}")
    String deletePerson(@PathVariable String id){
        if(!personaRepository.existsById(id)){
            throw new PersonNotFoundException(id);
        }
        personaRepository.deleteById(id);
        return  "Person with id "+id+" has been deleted success.";
    }

}
