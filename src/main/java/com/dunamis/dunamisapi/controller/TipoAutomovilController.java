package com.dunamis.dunamisapi.controller;

import com.dunamis.dunamisapi.exception.TipoAutomovilNotFoundException;
import com.dunamis.dunamisapi.model.Automovil;
import com.dunamis.dunamisapi.model.Direccion;
import com.dunamis.dunamisapi.model.Tipo;
import com.dunamis.dunamisapi.model.TipoAutomovil;
import com.dunamis.dunamisapi.repository.AutomovilRepository;
import com.dunamis.dunamisapi.repository.TipoAutomovilRepository;
import com.dunamis.dunamisapi.repository.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:5173/", " http://localhost:3000/", "http://localhost:5174/"})
public class TipoAutomovilController {

    @Autowired
    private TipoAutomovilRepository tipoAutomovilRepository;

    @Autowired
    private AutomovilRepository automovilRepository;

    @Autowired
    private TipoRepository tipoRepository;

    @PostMapping("/tipoAutomovil")
    public ResponseEntity<TipoAutomovil> newTipoAutomovil(@RequestBody Map<String, Object> tipoAutomovilDatos){
        try{
            TipoAutomovil tipoAutomovil = new TipoAutomovil();
            String idAutomovil = (String) tipoAutomovilDatos.get("automovil");
            Automovil automovil = automovilRepository.findById(idAutomovil).orElseThrow(null);
            Tipo tipo = tipoRepository.findById((int) tipoAutomovilDatos.get("tipo")).orElseThrow(null);

            if(automovil != null && tipoAutomovil != null){
                tipoAutomovil.setAutomovil(automovil);
                tipoAutomovil.setTipo(tipo);
            }else{
                throw new IllegalArgumentException("El tipo de automovil con el id " + idAutomovil + " no existe.");
            }
            System.out.println("Saving: " + tipoAutomovil.toString());
            TipoAutomovil saveTipoAutomovil = tipoAutomovilRepository.save(tipoAutomovil);
            return ResponseEntity.ok(saveTipoAutomovil);
        }catch (ConstraintViolationException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error de validacion ", e);
        }
    }

    @GetMapping("/tipoAutomoviles")
    List<TipoAutomovil> tipoAutomovilTodos(){return tipoAutomovilRepository.findAll();}

    @GetMapping("/tipoAutomovil/{id}")
    TipoAutomovil obtenerTipoAutmovilPorId(@PathVariable int id){
        return tipoAutomovilRepository.findById(id).orElseThrow(() -> new TipoAutomovilNotFoundException(id));
    }

    @DeleteMapping("/tipoAutomovil/{id}")
    String deleteTipoAutomovil(@PathVariable int id){
        if(!tipoAutomovilRepository.existsById(id)){
            throw new TipoAutomovilNotFoundException(id);
        }
        try{
            tipoAutomovilRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "No se puede eliminar la persona porque tiene reservas asociadas", e);
        }
        return "El Tipo Automovil con el id " + id + " ha sido eliminado satisfactoriamente";
    }
}
