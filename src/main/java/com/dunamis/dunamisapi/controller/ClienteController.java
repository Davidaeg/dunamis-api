package com.dunamis.dunamisapi.controller;

import com.dunamis.dunamisapi.exception.ClienteNotFoundException;
import com.dunamis.dunamisapi.model.Cliente;
import com.dunamis.dunamisapi.model.Persona;
import com.dunamis.dunamisapi.repository.ClienteRepository;
import com.dunamis.dunamisapi.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin(origins = {"http://localhost:5173/", "http://localhost:3000/", "http://localhost:5174/"})
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping("/cliente")
    public ResponseEntity<Cliente> newCliente(@RequestBody @Valid Map<String, Object> clientesDatos){
        try{
            Cliente cliente = new Cliente();
            String idPersona = (String) clientesDatos.get("idPersona");
            Persona persona = personaRepository.getById(idPersona);

            String fechaEmisionString = (String) clientesDatos.get("fecha_emision_licencia");
            String fechaVencimientoString = (String) clientesDatos.get("fecha_vencimiento_licencia");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date fechaEmisionDate = sdf.parse(fechaEmisionString);
            Date fechaVencimientoDate = sdf.parse(fechaVencimientoString);

            if(persona != null){
                cliente.setIdCliente((String) clientesDatos.get("idCliente"));
                cliente.setCategoriaLicencia((String) clientesDatos.get("categoria_licencia"));
                cliente.setFechaEmisionLicencia(fechaEmisionDate);
                cliente.setFechaVencimientoLicencia(fechaVencimientoDate);
                cliente.setEstado((String) clientesDatos.get("estado"));
                cliente.setPersona(persona);
            }else{
                throw new IllegalArgumentException("La persona con el id " + idPersona + " no existe");
            }
            System.out.println("Saving: " + cliente.toString());
            Cliente savedCliente = clienteRepository.save(cliente);
            return ResponseEntity.ok(savedCliente);
        }catch(ConstraintViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error de validacion ", e);
        }catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/cliente/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@RequestBody Cliente newCliente, @PathVariable String id) {
        try {
            Cliente clienteActualizado = clienteRepository.findById(id)
                    .map(cliente -> {
                        //cliente.setIdCliente(newCliente.getIdCliente());
                        cliente.setCategoriaLicencia(newCliente.getCategoriaLicencia());
                        cliente.setFechaEmisionLicencia(newCliente.getFechaEmisionLicencia());
                        cliente.setFechaVencimientoLicencia(newCliente.getFechaVencimientoLicencia());
                        cliente.setEstado(newCliente.getEstado());
                        return cliente;
                    })
                    .orElseThrow(() -> new ClienteNotFoundException(id));

            clienteActualizado = clienteRepository.save(clienteActualizado);
            return ResponseEntity.ok(clienteActualizado);
        } catch (IllegalArgumentException e) {
            throw new ClienteNotFoundException(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            throw new ClienteNotFoundException("Error al actualizar el cliente: " + e.getMessage());
        }
    }


    @GetMapping("/cliente")
    List<Cliente> direccionesTodas(){return clienteRepository.findAll();}

    @GetMapping("/cliente/{id}")
    Cliente obtenerClientePorId(@PathVariable String id){
        return clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));
    }

    @DeleteMapping("/cliente/{id}")
    String deleteCliente(@PathVariable String id){
        if(!clienteRepository.existsById(id)){
            throw new ClienteNotFoundException(id);
        }
        clienteRepository.deleteById(id);
        return "El cliente con el id  " + id + "  ha sido eliminado satisfactoriamente";
    }
}
