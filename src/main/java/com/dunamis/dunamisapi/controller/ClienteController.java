package com.dunamis.dunamisapi.controller;

import com.dunamis.dunamisapi.exception.ClienteNotFoundException;
import com.dunamis.dunamisapi.exception.DireccionPersonNotFoundException;
import com.dunamis.dunamisapi.model.Cliente;
import com.dunamis.dunamisapi.model.Direccion;
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
import java.time.LocalDate;
import java.util.*;
import java.time.ZoneId;


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
    public ResponseEntity<Cliente> newCliente(@RequestBody @Valid Map<String, Object> clienteDatos) {
        try {
            Cliente cliente = new Cliente();

            // Obtén los datos del cliente desde el mapa
            String idPersona = (String) clienteDatos.get("idPersona");
            Persona persona = personaRepository.findById(idPersona).orElse(null);

            if (persona != null) {
                cliente.setIdCliente((String) clienteDatos.get("idCliente"));
                cliente.setCategoriaLicencia((String) clienteDatos.get("categoriaLicencia"));

                LocalDate fechaEmisionLocalDate = LocalDate.parse((String) clienteDatos.get("fechaEmisionLicencia"));
                Date fechaEmisionDate = Date.from(fechaEmisionLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                cliente.setFechaEmisionLicencia(fechaEmisionDate);

                LocalDate fechaVencimientoLocalDate = LocalDate.parse((String) clienteDatos.get("fechaVencimientoLicencia"));
                Date fechaVencimientoDate = Date.from(fechaVencimientoLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                cliente.setFechaVencimientoLicencia(fechaVencimientoDate);

                cliente.setEstado((String) clienteDatos.get("estado"));
                cliente.setPersona(persona);
            } else {
                throw new IllegalArgumentException("La persona con el id " + idPersona + " no existe");
            }

            System.out.println("Saving: " + cliente.toString());
            Cliente saveCliente = clienteRepository.save(cliente);
            return ResponseEntity.ok(saveCliente);
        } catch (ConstraintViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error de validacion ", e);
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

    @PutMapping("/ClientePorPersona/{idPersona}")
    public List<Cliente> actualizarClientePorIdPersona(@PathVariable @Valid String idPersona, @RequestBody Cliente nuevoCliente) {
        List<Cliente> clientes = clienteRepository.findByPersona_IdPersona(idPersona);

        if (clientes.isEmpty()) {
            throw new DireccionPersonNotFoundException(idPersona);
        }

        for (Cliente cliente : clientes) {
            cliente.setIdCliente(nuevoCliente.getIdCliente());
            cliente.setCategoriaLicencia(nuevoCliente.getCategoriaLicencia());
            cliente.setFechaEmisionLicencia(nuevoCliente.getFechaEmisionLicencia());
            cliente.setFechaVencimientoLicencia(nuevoCliente.getFechaVencimientoLicencia());
            cliente.setEstado(nuevoCliente.getEstado());
            clienteRepository.save(cliente);
        }

        return clientes;
    }


    @GetMapping("/cliente")
    List<Cliente> direccionesTodas(){return clienteRepository.findAll();}

    @GetMapping("/cliente/{id}")
    Cliente obtenerClientePorId(@PathVariable String id){
        return clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));
    }

    @GetMapping("/clientePersona/{idPersona}")
    public List<Cliente> obtenerClientesPorIdPersona(@PathVariable String idPersona){
        return clienteRepository.findByPersona_IdPersona(idPersona);
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
