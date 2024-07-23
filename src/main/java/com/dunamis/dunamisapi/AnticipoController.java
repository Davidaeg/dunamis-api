package com.dunamis.dunamisapi;

import com.dunamis.dunamisapi.exception.AnticipoNotFoundException;
import com.dunamis.dunamisapi.model.Anticipo;
import com.dunamis.dunamisapi.repository.AnticipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:5173/", " http://localhost:3000/", "http://localhost:5174/"})
public class AnticipoController {
    @Autowired
    private AnticipoRepository anticipoRepository;

    @PostMapping("/anticipo")
    Anticipo newAnticipo(@RequestBody Anticipo newAnticipo){
        return anticipoRepository.save(newAnticipo);
    }

    @GetMapping("/anticipo")
    List<Anticipo> anticiposTodos(){return anticipoRepository.findAll();}

    @GetMapping("/anticipo/{id}")
    Anticipo anticipoPorId(@PathVariable int id){
        return anticipoRepository.findById(id).orElseThrow(() -> new AnticipoNotFoundException(id));
    }

    @PutMapping("/anticipo/{id}")
    Anticipo actualizarAnticipo(@RequestBody Anticipo newAnticipo, @PathVariable int id){
        return anticipoRepository.findById(id).map(anticipo -> {
            anticipo.setValor(newAnticipo.getValor());
            return anticipoRepository.save(anticipo);
        }).orElseThrow(() -> new AnticipoNotFoundException(id));
    }

    @DeleteMapping("/anticipo/{id}")
    String deleteAnticipo(@PathVariable int id){
        if(!anticipoRepository.existsById(id)){
            throw new AnticipoNotFoundException(id);
        }
        anticipoRepository.deleteById(id);
        return "Anticipo with id " + id + " has been deteled success";
    }
}
