package com.dunamis.dunamisapi.controller;

import com.dunamis.dunamisapi.exception.DireccionNotFoundException;
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
@CrossOrigin(origins = {"http://localhost:5173/", "http://localhost:3000/", "http://localhost:5174/"})
public class DireccionController {

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @PostMapping("/direccion")
    public ResponseEntity<Direccion> newDireccion(@RequestBody @Valid Map<String, Object> direccionDatos){
        try{
            Direccion direccion = new Direccion();
            String idPersona = (String) direccionDatos.get("idPersona");
            Persona persona = personaRepository.findById(idPersona).orElse(null);

            if(persona != null){
                direccion.setIdDireccion((int) direccionDatos.get("idDireccion"));
                direccion.setDireccion((String) direccionDatos.get("Direccion"));
                direccion.setProvincia((String) direccionDatos.get("Provincia"));
                direccion.setCanton((String) direccionDatos.get("Canton"));
                direccion.setDistrito((String) direccionDatos.get("Distrito"));
                direccion.setPersona(persona);
            }else{
                throw new IllegalArgumentException("La persona con el id " + idPersona + " no existe");
            }
            System.out.println("Saving: " + direccion.toString());
            Direccion saveDireccion = direccionRepository.save(direccion);
            return ResponseEntity.ok(saveDireccion);
        }catch (ConstraintViolationException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error de validacion ", e);
        }
    }

    @GetMapping("/direccion")
    List<Direccion> direccionesTodas(){return direccionRepository.findAll();}

    @GetMapping("/direcciones/{id}")
    Direccion obtenerDireccionPorId(@PathVariable int id){
        return direccionRepository.findById(id).orElseThrow(() -> new DireccionNotFoundException(id));
    }

    @PutMapping("/direcciones/{id}")
    Direccion actualizarDireccion(@RequestBody Direccion newDireccion, @PathVariable @Valid int id){
        return direccionRepository.findById(id)
                .map(direccion -> {
                    direccion.setIdDireccion((int)newDireccion.getIdDireccion());
                    direccion.setDireccion((String) newDireccion.getDireccion());
                    direccion.setProvincia((String) newDireccion.getProvincia());
                    direccion.setCanton((String) newDireccion.getCanton());
                    direccion.setDistrito((String) newDireccion.getDistrito());
                    return direccionRepository.save(direccion);
        }).orElseThrow(() -> new DireccionNotFoundException(id));
    }

    @DeleteMapping("/direcciones/{id}")
    String eliminarDireccion(@PathVariable int id){
        if(!direccionRepository.existsById(id)){
            throw new DireccionNotFoundException(id);
        }
        direccionRepository.deleteById(id);
        return "La direccion con el id " + id + " ha sido eliminada satisfactoriamente";
    }

}
