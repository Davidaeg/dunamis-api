package com.dunamis.dunamisapi.controller;

import com.dunamis.dunamisapi.exception.DireccionNotFoundException;
import com.dunamis.dunamisapi.exception.DireccionPersonNotFoundException;
import com.dunamis.dunamisapi.model.Direccion;
import com.dunamis.dunamisapi.model.Persona;
import com.dunamis.dunamisapi.repository.DireccionRepository;
import com.dunamis.dunamisapi.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class DireccionController {

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @PostMapping("/direccion")
    public ResponseEntity<Direccion> newDireccion(@RequestBody @Valid Map<String, Object> direccionDatos) {
        try {
            Direccion direccion = new Direccion();
            String idPersona = (String) direccionDatos.get("idPersona");
            Persona persona = personaRepository.findById(idPersona).orElse(null);

            if (persona != null) {
                direccion.setDireccion((String) direccionDatos.get("Direccion"));
                direccion.setProvincia((String) direccionDatos.get("Provincia"));
                direccion.setCanton((String) direccionDatos.get("Canton"));
                direccion.setDistrito((String) direccionDatos.get("Distrito"));
                direccion.setPersona(persona);
            } else {
                throw new IllegalArgumentException("La persona con el id " + idPersona + " no existe");
            }
            System.out.println("Saving: " + direccion.toString());
            Direccion saveDireccion = direccionRepository.save(direccion);
            return ResponseEntity.ok(saveDireccion);
        } catch (ConstraintViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error de validacion ", e);
        }
    }

    @GetMapping("/direcciones")
    List<Direccion> direccionesTodas(){return direccionRepository.findAll();}

    @GetMapping("/direccion/{id}")
    Direccion obtenerDireccionPorId(@PathVariable int id){
        return direccionRepository.findById(id).orElseThrow(() -> new DireccionNotFoundException(id));
    }

    @GetMapping("/direccionPersona/{idPersona}")
    public List<Direccion> obtenerDireccionesPorIdPersona(@PathVariable String idPersona){
        return direccionRepository.findByPersona_IdPersona(idPersona);
    }

    @PutMapping("/direccion/{id}")
    Direccion actualizarDireccion(@RequestBody Direccion newDireccion, @PathVariable @Valid int id){
        return direccionRepository.findById(id)
                .map(direccion -> {
                    direccion.setDireccion((String) newDireccion.getDireccion());
                    direccion.setProvincia((String) newDireccion.getProvincia());
                    direccion.setCanton((String) newDireccion.getCanton());
                    direccion.setDistrito((String) newDireccion.getDistrito());
                    return direccionRepository.save(direccion);
        }).orElseThrow(() -> new DireccionNotFoundException(id));
    }

    @PutMapping("/direccionPersona/{idPersona}")
    public List<Direccion> actualizarDireccionPorIdPersona(@PathVariable @Valid String idPersona, @RequestBody Direccion nuevaDireccion) {
        List<Direccion> direcciones = direccionRepository.findByPersona_IdPersona(idPersona);

        if (direcciones.isEmpty()) {
            throw new DireccionPersonNotFoundException(idPersona);
        }

        for (Direccion direccion : direcciones) {
            direccion.setDireccion(nuevaDireccion.getDireccion());
            direccion.setProvincia(nuevaDireccion.getProvincia());
            direccion.setCanton(nuevaDireccion.getCanton());
            direccion.setDistrito(nuevaDireccion.getDistrito());
            direccionRepository.save(direccion);
        }

        return direcciones;
    }

    @DeleteMapping("/direccion/{id}")
    String eliminarDireccion(@PathVariable int id){
        if(!direccionRepository.existsById(id)){
            throw new DireccionNotFoundException(id);
        }
        direccionRepository.deleteById(id);
        return "La direccion con el id " + id + " ha sido eliminada satisfactoriamente";
    }

    @DeleteMapping("/direccionIdPersona/{idPersona}")
    String eliminarDireccionporIdPersona(@PathVariable String idPersona) {
        List<Direccion> direcciones = direccionRepository.findByPersona_IdPersona(idPersona);
        if (direcciones.isEmpty()) {
            throw new DireccionPersonNotFoundException(idPersona);
        }
        direccionRepository.deleteAll(direcciones);
        return "Todas las direcciones asociadas a la persona con el id " + idPersona + " han sido eliminadas satisfactoriamente";
    }

}
