package com.dunamis.dunamisapi.controller;

import com.dunamis.dunamisapi.exception.ImpuestoNotFoundActive;
import com.dunamis.dunamisapi.exception.ImpuestoNotFoundException;
import com.dunamis.dunamisapi.model.Impuesto;
import com.dunamis.dunamisapi.repository.ImpuestoRepository;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:5173/", " http://localhost:3000/", "http://localhost:5174/"})
public class ImpuestoController {

    @Autowired
    private ImpuestoRepository impuestoRepository;

    @PostMapping("/impuesto")
    Impuesto newImpuesto(@RequestBody Impuesto newImpuesto){return impuestoRepository.save(newImpuesto);}

    @GetMapping("/impuesto")
    List<Impuesto> obtenerTodosLosImpuesto(){return impuestoRepository.findAll();}

    @GetMapping("/impuesto/{id}")
    Impuesto obtenerImpuestoPorId(@PathVariable int id){
        return impuestoRepository.findById(id).orElseThrow(()-> new ImpuestoNotFoundException(id));
    }

    @PutMapping("/impuesto/{id}")
    Impuesto actualizarImpuesto(@RequestBody Impuesto newImpuesto, @PathVariable int id){
        return impuestoRepository.findById(id).map(impuesto -> {
            impuesto.setActivo(newImpuesto.isActivo());
            impuesto.setNombre(newImpuesto.getNombre());
            impuesto.setPorcentaje(newImpuesto.getPorcentaje());
            return impuestoRepository.save(impuesto);
        }).orElseThrow(()-> new ImpuestoNotFoundException(id));
    }

    @DeleteMapping("/impuesto/{id}")
    String deleteImpuesto(@PathVariable int id){
        if(!impuestoRepository.existsById(id)){
            throw new ImpuestoNotFoundException(id);
        }
        impuestoRepository.deleteById(id);
        return "El impuesto con el id " + id + " ha sido eliminado satisfactoriamente";

    }
}
