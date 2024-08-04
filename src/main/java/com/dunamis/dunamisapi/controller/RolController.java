package com.dunamis.dunamisapi.controller;

import com.dunamis.dunamisapi.exception.RolNotFoundException;
import com.dunamis.dunamisapi.model.Rol;
import com.dunamis.dunamisapi.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:5173/", " http://localhost:3000/", "http://localhost:5174/"})
public class RolController {

    @Autowired
    private RolRepository rolRepository;

    @PostMapping("/rol")
    Rol nuevoRol(@RequestBody Rol newRol){return  rolRepository.save(newRol);}

    @GetMapping("/rol")
    List<Rol> obtenerTodosLosRoles(){return rolRepository.findAll();}

    @GetMapping("/rol/{id}")
    Rol obtenerRolPorId(@PathVariable int id){
        return rolRepository.findById(id).orElseThrow(()-> new RolNotFoundException(id));
    }

    @PutMapping("/rol/{id}")
    Rol actualizarRol(@RequestBody Rol newRol, @PathVariable int id){
        return rolRepository.findById(id).map(rol -> {
            rol.setNombre(newRol.getNombre());
            rol.setDescripcion(newRol.getDescripcion());
            return rolRepository.save(rol);
        }).orElseThrow(()-> new RolNotFoundException(id));
    }

    @DeleteMapping("/rol/{id}")
    String deleteRol(@PathVariable int id){
        if(!rolRepository.existsById(id)){
            throw new RolNotFoundException(id);
        }
        rolRepository.deleteById(id);
        return "El rol con el id " + id + " ha sido eliminado satisfactoriamente.";
    }
}
