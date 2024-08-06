package com.dunamis.dunamisapi.controller;

import com.dunamis.dunamisapi.exception.AutomovilNotFoundException;
import com.dunamis.dunamisapi.model.Automovil;
import com.dunamis.dunamisapi.model.Segmento;
import com.dunamis.dunamisapi.repository.AutomovilRepository;
import com.dunamis.dunamisapi.repository.SegmentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
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
    public ResponseEntity<Automovil> newAutomovil(@RequestBody Map<String, Object> automovilDatos){
        try{
            Automovil auto = new Automovil();
            int idSegemento = (int) automovilDatos.get("segmento_id_segmento");
            Segmento segmento = segmentoRepository.getById(idSegemento);

            if(segmento != null){
                auto.setPlaca((String) automovilDatos.get("placa"));
                auto.setTransmision((String) automovilDatos.get("transmision"));
                auto.setAnno((int) automovilDatos.get("anno"));
                auto.setAutomovilActivo((boolean) automovilDatos.get("automovil_activo"));
                auto.setCabina((String) automovilDatos.get("cabina"));
                auto.setCarroceria((String) automovilDatos.get("carroceria"));
                auto.setColor((String) automovilDatos.get("color"));
                auto.setCombustible((String) automovilDatos.get("combustible"));
                auto.setCosto((double) automovilDatos.get("costo"));
                auto.setEstilo((String) automovilDatos.get("estilo"));
                auto.setMarca((String) automovilDatos.get("marca"));
                auto.setModelo((String) automovilDatos.get("modelo"));
                auto.setTraccion((String) automovilDatos.get("traccion"));
                auto.setSegmento(segmento);
            }else{
                throw new IllegalArgumentException("El segmento con el id " + idSegemento + " no existe");
            }
            System.out.println("Saving: " + automovilDatos.toString());
            Automovil saeAutomovil = automovilRepository.save(auto);
            return ResponseEntity.ok(saeAutomovil);
        }catch (ConstraintViolationException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error de validacion ", e);
        }
    }

    @GetMapping("/automovil")
    List<Automovil> automovilesTodos(){return automovilRepository.findAll();}

    @GetMapping("/automovil/{id}")
    Automovil obtenerAutomovilPorId(@PathVariable String id){
        return  automovilRepository.findById(id).orElseThrow(()-> new AutomovilNotFoundException(id));
    }

    @PutMapping("/automovil/{id}")
    Automovil actualizarAutomovil(@RequestBody Automovil automovil, @PathVariable String id){
        return automovilRepository.findById(id).map(auto ->{
            auto.setAutomovilActivo((boolean) automovil.isAutomovilActivo());
            auto.setColor((String) automovil.getColor());
            auto.setCosto((Double) automovil.getCosto());
            return automovilRepository.save(auto);
        }).orElseThrow(()-> new AutomovilNotFoundException(id));
    }

    @DeleteMapping("/automovil/{id}")
    String deleteAutomovil(@PathVariable String id){
        if(!automovilRepository.existsById(id)){
            throw new AutomovilNotFoundException(id);
        }
        automovilRepository.deleteById(id);
        return "El automovil con el id " + id + " ha sido eliminado satisfactoriamente";
    }
}
