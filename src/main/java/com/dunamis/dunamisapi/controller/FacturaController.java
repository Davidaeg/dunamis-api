package com.dunamis.dunamisapi.controller;

import com.dunamis.dunamisapi.exception.FacturaNotFoundException;
import com.dunamis.dunamisapi.model.Factura;
import com.dunamis.dunamisapi.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:5173/", " http://localhost:3000/", "http://localhost:5174/"})
public class FacturaController {

    @Autowired
    private FacturaRepository facturaRepository;

    @PostMapping("/factura")
    Factura newFactura(@RequestBody Factura newFactura){
        return facturaRepository.save(newFactura);
    }

    @GetMapping("/factura")
    List<Factura> facturasTodas(){return facturaRepository.findAll();}

    @GetMapping("/factura/{id}")
    Factura facturaPorId(@PathVariable int id){
        return facturaRepository.findById(id).orElseThrow(() -> new FacturaNotFoundException(id));
    }

    @DeleteMapping("/factura/{id}")
    String deleteFactura(@PathVariable int id){
        if(!facturaRepository.existsById(id)){
            throw new FacturaNotFoundException(id);
        }
        facturaRepository.deleteById(id);
        return "Factura with id " + id + " has been deleted success";
    }


}
