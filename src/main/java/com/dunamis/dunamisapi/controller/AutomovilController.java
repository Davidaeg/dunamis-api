package com.dunamis.dunamisapi.controller;

import com.dunamis.dunamisapi.exception.AutomovilNotFoundException;
import com.dunamis.dunamisapi.model.Automovil;
import com.dunamis.dunamisapi.model.Segmento;
import com.dunamis.dunamisapi.repository.AutomovilRepository;
import com.dunamis.dunamisapi.repository.SegmentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:5173/", " http://localhost:3000/", "http://localhost:5174/"})
public class AutomovilController {

    @Autowired
    private AutomovilRepository automovilRepository;
    @Autowired
    private SegmentoRepository segmentoRepository;


    @PostMapping("/automovil")
    public ResponseEntity<Automovil> nuevoAutomovil(@RequestBody Map<String, Object> automovilData) {
        try{

        Automovil automovil = new Automovil();

        int idSegmento = (int) automovilData.get("idSegmento");

        Segmento segmento = segmentoRepository.findById(idSegmento).orElse(null);

        if (segmento != null) {
            automovil.setPlaca((String) automovilData.get("placa"));
            automovil.setSegmento(segmento);
            automovil.setMarca((String) automovilData.get("marca"));
            automovil.setModelo((String) automovilData.get("modelo"));
            automovil.setAnno((int) automovilData.get("anno"));
            automovil.setColor((String) automovilData.get("color"));
            automovil.setEstilo((String) automovilData.get("estilo"));
            automovil.setCarroceria((String) automovilData.get("carroceria"));
            automovil.setCombustible((String) automovilData.get("combustible"));
            automovil.setCabina((String) automovilData.get("cabina"));
            automovil.setTraccion((String) automovilData.get("traccion"));
            automovil.setTransmision((String) automovilData.get("transmision"));
            automovil.setCosto((double) automovilData.get("costo"));
        } else {
            return ResponseEntity.badRequest().build();
        }
            System.out.println("Saving: "+ automovil.toString());
        Automovil savedAutomovil = automovilRepository.save(automovil);
        return ResponseEntity.ok(savedAutomovil);
        }catch (Error e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/automoviles")
    List<Automovil> getAllAutomoviles() {
        return automovilRepository.findAll();
    }

    @GetMapping("/carro/{id}")
    Automovil getAutomovilById(@PathVariable long id) {
        return automovilRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new AutomovilNotFoundException(id));
    }

    @PutMapping("/carros/{id}")
    Automovil updateAutomovil(@RequestBody Automovil newAutomovil, @PathVariable Long id) {
        return automovilRepository.findById(String.valueOf(id))
                .map(automovil -> {
                    automovil.setPlaca(newAutomovil.getPlaca());
                    automovil.setTransmision(newAutomovil.getTransmision());
                    automovil.setAnno(newAutomovil.getAnno());
                    automovil.setCabina(newAutomovil.getCabina());
                    automovil.setCarroceria(newAutomovil.getCarroceria());
                    automovil.setColor(newAutomovil.getColor());
                    automovil.setCombustible(newAutomovil.getCombustible());
                    automovil.setCosto(newAutomovil.getCosto());
                    automovil.setEstilo(newAutomovil.getEstilo());
                    automovil.setMarca(newAutomovil.getMarca());
                    automovil.setModelo(newAutomovil.getModelo());
                    automovil.setTraccion(newAutomovil.getTraccion());
                    automovil.setSegmento(newAutomovil.getSegmento());
                    return automovilRepository.save(automovil);
                }).orElseThrow(() -> new AutomovilNotFoundException(id));
    }

    @DeleteMapping("/automovilD/{id}")
    String deleteAutomovil(@PathVariable Long id){
        if(!automovilRepository.existsById(String.valueOf(id))){
            throw new AutomovilNotFoundException(id);
        }
        automovilRepository.deleteById(String.valueOf(id));
        return "Automovil with id " + id + " has been deleted successfully.";
    }
}
