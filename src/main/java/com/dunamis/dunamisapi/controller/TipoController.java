package com.dunamis.dunamisapi.controller;

import com.dunamis.dunamisapi.exception.TipoNotFoundException;
import com.dunamis.dunamisapi.model.Tipo;
import com.dunamis.dunamisapi.repository.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:5173/", "http://localhost:3000/", "http://localhost:5174/"})
public class TipoController {

    @Autowired
    private TipoRepository tipoRepository;

    @PostMapping("/tipo")
    Tipo newTipo(@RequestBody Tipo newTipo){return tipoRepository.save(newTipo);}

    @GetMapping("/tipo")
    List<Tipo> tiposTodos(){return tipoRepository.findAll();}

    @GetMapping("/tipo/{id}")
    Tipo obtenerTipoPorId(@PathVariable int id){
        return tipoRepository.findById(id).orElseThrow(() -> new TipoNotFoundException(id));
    }

    @PutMapping("/tipo/{id}")
    Tipo actualizarTipo(@RequestBody Tipo newTipo, @PathVariable int id){
        return tipoRepository.findById(id).map(tipo -> {
            tipo.setNombre(newTipo.getNombre());
            return tipoRepository.save(tipo);
        }).orElseThrow(() -> new TipoNotFoundException(id));
    }

    @DeleteMapping("/tipo/{id}")
    String deleteTipo(@PathVariable int id){
        if(!tipoRepository.existsById(id)){
            throw new TipoNotFoundException(id);
        }
        tipoRepository.deleteById(id);
        return "Tipo de automovil con el id " + id + " fue eliminado satisfactoriamente.";
    }
}
