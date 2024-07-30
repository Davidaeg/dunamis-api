package com.dunamis.dunamisapi.controller;

import com.dunamis.dunamisapi.exception.TipoAutomovilNotFoundException;
import com.dunamis.dunamisapi.model.TipoAutomovil;
import com.dunamis.dunamisapi.model.Tipo;
import com.dunamis.dunamisapi.model.Automovil;
import com.dunamis.dunamisapi.repository.TipoAutomovilRepository;
import com.dunamis.dunamisapi.repository.TipoRepository;
import com.dunamis.dunamisapi.repository.AutomovilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:5173/", "http://localhost:3000/", "http://localhost:5174/"})
public class TipoAutomovilController {

    @Autowired
    private TipoAutomovilRepository tipoAutomovilRepository;

    @Autowired
    private TipoRepository tipoRepository;

    @Autowired
    private AutomovilRepository automovilRepository;

    @PostMapping("/tipo-automovil")
    public ResponseEntity<TipoAutomovil> nuevoTipoAutomovil(@RequestBody Map<String, Object> tipoAutomovilData) {
        try {
            TipoAutomovil tipoAutomovil = new TipoAutomovil();

            int tipoId = (int) tipoAutomovilData.get("tipoId");
            Tipo tipo = tipoRepository.findById(tipoId).orElse(null);

            String automovilPlaca = (String) tipoAutomovilData.get("automovilPlaca");
            Automovil automovil = automovilRepository.findById(automovilPlaca).orElse(null);

            if (tipo != null && automovil != null) {
                tipoAutomovil.setTipo(tipo);
                tipoAutomovil.setAutomovil(automovil);
            } else {
                return ResponseEntity.badRequest().build();
            }

            TipoAutomovil savedTipoAutomovil = tipoAutomovilRepository.save(tipoAutomovil);
            return ResponseEntity.ok(savedTipoAutomovil);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/tipo-automoviles")
    public List<TipoAutomovil> getAllTipoAutomoviles() {
        return tipoAutomovilRepository.findAll();
    }

    @GetMapping("/tipo-automovil/{id}")
    public TipoAutomovil getTipoAutomovilById(@PathVariable int id) {
        return tipoAutomovilRepository.findById(id)
                .orElseThrow(() -> new TipoAutomovilNotFoundException((long) id));
    }

    @PutMapping("/tipo-automovil/{id}")
    public TipoAutomovil updateTipoAutomovil(@RequestBody TipoAutomovil newTipoAutomovil, @PathVariable int id) {
        return tipoAutomovilRepository.findById(id)
                .map(tipoAutomovil -> {
                    tipoAutomovil.setTipo(newTipoAutomovil.getTipo());
                    tipoAutomovil.setAutomovil(newTipoAutomovil.getAutomovil());
                    return tipoAutomovilRepository.save(tipoAutomovil);
                }).orElseThrow(() -> new TipoAutomovilNotFoundException((long) id));
    }

    @DeleteMapping("/tipo-automovil/{id}")
    public String deleteTipoAutomovil(@PathVariable int id) {
        if (!tipoAutomovilRepository.existsById(id)) {
            throw new TipoAutomovilNotFoundException((long) id);
        }
        tipoAutomovilRepository.deleteById(id);
        return "TipoAutomovil with id " + id + " has been deleted successfully.";
    }
}

