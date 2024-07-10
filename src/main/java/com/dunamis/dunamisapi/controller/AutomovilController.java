package com.dunamis.dunamisapi.controller;


import com.dunamis.dunamisapi.exception.AutomovilNotFoundException;
import com.dunamis.dunamisapi.model.Automovil;
import com.dunamis.dunamisapi.repository.AutomovilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:5173/", " http://localhost:3000/", "http://localhost:5174/"})

public class AutomovilController {
    @Autowired
    private AutomovilRepository automovilRepository;

    @PostMapping("/automovil")
    Automovil newAutomovil(@RequestBody Automovil newAutomovil) {
        if (newAutomovil.getPlaca() == null || newAutomovil.getPlaca().isEmpty()) {
            throw new IllegalArgumentException("placaAutomovil cannot be null or empty");
        }
        return automovilRepository.save(newAutomovil);
    }

    @GetMapping("/automoviles")
    List<Automovil> getAllAutomobiles() {
        return automovilRepository.findAll();
    }

    @GetMapping("/automovil/{id}")
    Automovil getAutomobileById(@PathVariable String id) {
        return automovilRepository.findById(id)
                .orElseThrow(() -> new AutomovilNotFoundException(id));
    }

    @PutMapping("/automovil/{id}")
    Automovil updateAutomobile(@RequestBody Automovil newAutomovil, @PathVariable String id) {
        return automovilRepository.findById(id)
                .map(auto -> {
                    auto.setMarca(newAutomovil.getMarca());
                    auto.setModelo(newAutomovil.getModelo());
                    auto.setAnno(newAutomovil.getAnno());
                    auto.setReservaciones(newAutomovil.getReservaciones());
                    auto.setTipoAutomoviles(newAutomovil.getTipoAutomoviles());
                    return automovilRepository.save(auto);
                }).orElseThrow(() -> new AutomovilNotFoundException(id));
    }

    @DeleteMapping("/automovil/{id}")
    String deletePerson(@PathVariable String id){
        if(!automovilRepository.existsById(id)){
            throw new AutomovilNotFoundException(id);
        }
        automovilRepository.deleteById(id);
        return  "Automobile with id "+id+" has been deleted success.";
    }
}
