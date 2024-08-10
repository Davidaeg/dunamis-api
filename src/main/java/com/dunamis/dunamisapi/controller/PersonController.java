package com.dunamis.dunamisapi.controller;

import com.dunamis.dunamisapi.exception.PersonNotFoundException;
import com.dunamis.dunamisapi.model.Cliente;
import com.dunamis.dunamisapi.model.Direccion;
import com.dunamis.dunamisapi.model.Persona;
import com.dunamis.dunamisapi.model.Usuario;
import com.dunamis.dunamisapi.repository.ClienteRepository;
import com.dunamis.dunamisapi.repository.PersonaRepository;
import com.dunamis.dunamisapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.dunamis.dunamisapi.repository.DireccionRepository;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.ZoneId;
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

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/persona")
    public ResponseEntity<Persona> newPersona(@RequestBody @Valid Map<String, Object> personaDatos) {
        try {
            Persona persona = new Persona();

            // Extract and validate persona data
            String idPersona = (String) personaDatos.get("idPersona");
            if (idPersona == null || idPersona.isEmpty()) {
                throw new IllegalArgumentException("El id de la persona no puede ser nulo o vacio");
            }
            persona.setIdPersona(idPersona);

            String nombre = (String) personaDatos.get("nombre");
            if (nombre == null || nombre.isEmpty()) {
                throw new IllegalArgumentException("El nombre de la persona no puede ser nulo o vacio");
            }
            persona.setNombre(nombre);

            String apellido1 = (String) personaDatos.get("apellido1");
            if (apellido1 == null || apellido1.isEmpty()) {
                throw new IllegalArgumentException("El apellido paterno de la persona no puede ser nulo o vacio");
            }
            persona.setApellido1(apellido1);

            String apellido2 = (String) personaDatos.get("apellido2");
            if (apellido2 == null || apellido2.isEmpty()) {
                throw new IllegalArgumentException("El apellido materno de la persona no puede ser nulo o vacio");
            }
            persona.setApellido2(apellido2);

            String email = (String) personaDatos.get("email");
            if (email == null || email.isEmpty()) {
                throw new IllegalArgumentException("El email de la persona no puede ser nulo o vacio");
            }
            persona.setEmail(email);

            String numeroCelular = (String) personaDatos.get("numeroCelular");
            if (numeroCelular == null || numeroCelular.isEmpty()) {
                throw new IllegalArgumentException("El numero de celular de la persona no puede ser nulo o vacio");
            }
            persona.setNumeroCelular(numeroCelular);

            String numeroTelefono = (String) personaDatos.get("numeroTelefono");
            if (numeroTelefono == null || numeroTelefono.isEmpty()) {
                throw new IllegalArgumentException("El numero de telefono de la persona no puede ser nulo o vacio");
            }
            persona.setNumeroTelefono(numeroTelefono);

            String fechaNacimiento = (String) personaDatos.get("fechaNacimiento");
            if (fechaNacimiento == null || fechaNacimiento.isEmpty()) {
                throw new IllegalArgumentException("La fecha de nacimiento de la persona no puede ser nulo o vacio");
            }
            LocalDate fechaNacimientoLocalDate = LocalDate.parse(fechaNacimiento);
            Date fechaNacimientoDate = Date.from(fechaNacimientoLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            persona.setFechaNacimiento(fechaNacimientoDate);

            System.out.println("Saving: " + persona.toString());
            Persona savePersona = personaRepository.save(persona);
            return ResponseEntity.ok(savePersona);
        } catch (ConstraintViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error de validacion ", e);
        }
    }

    @GetMapping("/personas")
    List<Persona> personasTodas(){return personaRepository.findAll();}

    @GetMapping("/persona/{id}")
    Persona obtenerPersonaPorId(@PathVariable String id){
        return personaRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    @PutMapping("/persona/{id}")
    public ResponseEntity<Persona> updatePersona(@RequestBody Map<String, Object> personaDatos, @PathVariable String id) {
        return personaRepository.findById(id).map(persona -> {
            try {
                String nombre = (String) personaDatos.get("nombre");
                if (nombre != null && !nombre.isEmpty()) {
                    persona.setNombre(nombre);
                }

                String apellido1 = (String) personaDatos.get("apellido1");
                if (apellido1 != null && !apellido1.isEmpty()) {
                    persona.setApellido1(apellido1);
                }

                String apellido2 = (String) personaDatos.get("apellido2");
                if (apellido2 != null && !apellido2.isEmpty()) {
                    persona.setApellido2(apellido2);
                }

                String fechaNacimiento = (String) personaDatos.get("fechaNacimiento");
                if (fechaNacimiento != null && !fechaNacimiento.isEmpty()) {
                    LocalDate fechaNacimientoLocalDate = LocalDate.parse(fechaNacimiento);
                    Date fechaNacimientoDate = Date.from(fechaNacimientoLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    persona.setFechaNacimiento(fechaNacimientoDate);
                }

                String numeroTelefono = (String) personaDatos.get("numeroTelefono");
                if (numeroTelefono != null && !numeroTelefono.isEmpty()) {
                    persona.setNumeroTelefono(numeroTelefono);
                }

                String numeroCelular = (String) personaDatos.get("numeroCelular");
                if (numeroCelular != null && !numeroCelular.isEmpty()) {
                    persona.setNumeroCelular(numeroCelular);
                }

                String email = (String) personaDatos.get("email");
                if (email != null && !email.isEmpty()) {
                    persona.setEmail(email);
                }

                // Guarda y devuelve la persona actualizada
                Persona updatedPersona = personaRepository.save(persona);
                return ResponseEntity.ok(updatedPersona);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al actualizar la persona", e);
            }
        }).orElseThrow(() -> new PersonNotFoundException(id));
    }

    @DeleteMapping("/persona/{id}")
    String deletePersona(@PathVariable String id){
        if(!personaRepository.existsById(id)){
            throw new PersonNotFoundException(id);
        }

        try {
            List<Direccion> direcciones = direccionRepository.findByPersona_IdPersona(id);
            direccionRepository.deleteAll(direcciones);

            List<Cliente> clientes = clienteRepository.findByPersona_IdPersona(id);
            clienteRepository.deleteAll(clientes);

            List<Usuario> usuarios = usuarioRepository.findByPersona_IdPersona(id);
            usuarioRepository.deleteAll(usuarios);

            personaRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "No se puede eliminar la persona porque tiene reservas asociadas", e);
        }

        return "La persona con el id " + id + " ha sido eliminada satisfactoriamente";
    }

}
