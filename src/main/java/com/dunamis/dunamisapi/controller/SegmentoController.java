package com.dunamis.dunamisapi.controller;

import com.dunamis.dunamisapi.exception.DireccionNotFoundException;
import com.dunamis.dunamisapi.exception.SegmentoNotFoundException;
import com.dunamis.dunamisapi.model.Segmento;
import com.dunamis.dunamisapi.repository.SegmentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:5173/", " http://localhost:3000/", "http://localhost:5174/"})
public class SegmentoController {

    @Autowired
    private SegmentoRepository segmentoRepository;

    @PostMapping("/segmento")
    Segmento newSegmento(@RequestBody Segmento newSegmento){return segmentoRepository.save(newSegmento);}

    @GetMapping("/segmento")
    List<Segmento> segmentoTodos(){return segmentoRepository.findAll();}

    @GetMapping("/segmento/{id}")
    Segmento obtenerSegmentoPorId(@PathVariable int id){
        return segmentoRepository.findById(id).orElseThrow(() -> new SegmentoNotFoundException(id));
    }

    @PutMapping("/segmento/{id}")
    Segmento actualizarSegmento(@RequestBody Segmento newSegmento, @PathVariable int id){
        return segmentoRepository.findById(id).map(segmento -> {
            segmento.setNombre(newSegmento.getNombre());
            return segmentoRepository.save(segmento);
        }).orElseThrow(()-> new SegmentoNotFoundException(id));
    }

    @DeleteMapping("/segmento/{id}")
    String deleteSegmento(@PathVariable int id){
        if(!segmentoRepository.existsById(id)){
            throw new SegmentoNotFoundException(id);
        }
        segmentoRepository.deleteById(id);
        return "El segmento con el id " + id + " ha sido eliminado satisfactoriamente";
    }
}
