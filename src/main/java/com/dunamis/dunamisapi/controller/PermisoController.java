package com.dunamis.dunamisapi.controller;

import com.dunamis.dunamisapi.exception.PermisoNotFoundException;
import com.dunamis.dunamisapi.model.Permisos;
import com.dunamis.dunamisapi.repository.PermisosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:5173/", " http://localhost:3000/", "http://localhost:5174/"})
public class PermisoController {

    @Autowired
    private PermisosRepository permisosRepository;

    @PostMapping("/permiso")
    Permisos newPermiso(@RequestBody Permisos newPermiso){return permisosRepository.save(newPermiso);}

    @GetMapping("/permiso")
    List<Permisos> obtenerTodosLosPermisos(){return permisosRepository.findAll();}

    @GetMapping("/permiso/{id}")
    Permisos obtenerPermisosPorId(@PathVariable int id){
        return permisosRepository.findById(id).orElseThrow(()-> new PermisoNotFoundException(id));
    }

    @PutMapping("/permiso/{id}")
    Permisos actualizarPermisos(@RequestBody Permisos newPermiso, @PathVariable int id){
        return permisosRepository.findById(id).map(permisos -> {
            permisos.setDescripcion(newPermiso.getDescripcion());
            return permisosRepository.save(permisos);
        }).orElseThrow(()-> new PermisoNotFoundException(id));
    }

    @DeleteMapping("/permiso/{id}")
    String deletePermisos(@PathVariable int id){
        if(!permisosRepository.existsById(id)){
            throw new PermisoNotFoundException(id);
        }
        permisosRepository.deleteById(id);
        return "El permiso con el id " + id + " ha sido eliminado satisfactoriamente.";
    }
}
