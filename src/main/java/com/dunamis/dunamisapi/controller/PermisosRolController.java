package com.dunamis.dunamisapi.controller;

import com.dunamis.dunamisapi.exception.PermisosRolNotFoundException;
import com.dunamis.dunamisapi.model.Permisos;
import com.dunamis.dunamisapi.model.PermisosRol;
import com.dunamis.dunamisapi.model.Rol;
import com.dunamis.dunamisapi.repository.PermisosRepository;
import com.dunamis.dunamisapi.repository.PermisosRolRepository;
import com.dunamis.dunamisapi.repository.RolRepository;
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
public class PermisosRolController {

    @Autowired
    private PermisosRolRepository permisosRolRepository;

    @Autowired
    private PermisosRepository permisosRepository;

    @Autowired
    private RolRepository rolRepository;

    @PostMapping("/permisos-rol")
    public ResponseEntity<PermisosRol> newPermisoRol(@RequestBody Map<String, Object> permisosRolDatos){
        try{
            PermisosRol permisosRol = new PermisosRol();
            int idPermisos = (int) permisosRolDatos.get("permisos_permisosid");
            Permisos permisos = permisosRepository.getById(idPermisos);
            int idRol = (int) permisosRolDatos.get("rol_id_rol");
            Rol rol = rolRepository.getById(idRol);

            if(permisos != null && rol != null){
                permisosRol.setId((int) permisosRolDatos.get("id"));
                permisosRol.setPermisos(permisos);
                permisosRol.setRol(rol);
            }else{
                throw new IllegalArgumentException("El permiso con el id " + idPermisos + " o el rol con el id " + idRol+ " no existe");
            }
            System.out.println("Saving: " + permisosRolDatos.toString());
            PermisosRol savedPermisosRol = permisosRolRepository.save(permisosRol);
            return ResponseEntity.ok(savedPermisosRol);
        }catch (ConstraintViolationException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error de validacion ", e);
        }
    }

    @GetMapping("/permisos-rol")
    List<PermisosRol> obtenerTodosLosPermisosRol(){return permisosRolRepository.findAll();}

    @GetMapping("/permisos-rol/{id}")
    PermisosRol obtenerPermisoRolPorId(@PathVariable int id){
        return permisosRolRepository.findById(id).orElseThrow(()-> new PermisosRolNotFoundException(id));
    }

    /*@DeleteMapping("/permisos-rol")
    String deletePermisosRol(@PathVariable int id){
        if(!permisosRolRepository.existsById(id)){
            throw new PermisosRolNotFoundException(id);
        }
        permisosRolRepository.deleteById(id);
        return "El permiso rol con el id " + id + " ha sido eliminado satisfactoriamente.";
    }*/
}
