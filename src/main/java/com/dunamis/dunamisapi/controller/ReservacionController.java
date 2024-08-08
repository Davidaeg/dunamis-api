package com.dunamis.dunamisapi.controller;

import com.dunamis.dunamisapi.dto.AutomovilDTO;
import com.dunamis.dunamisapi.dto.ReservacionDTO;
import com.dunamis.dunamisapi.exception.ReservacionNotFoundException;
import com.dunamis.dunamisapi.model.Automovil;
import com.dunamis.dunamisapi.model.Cliente;
import com.dunamis.dunamisapi.model.Reservacion;
import com.dunamis.dunamisapi.repository.AutomovilRepository;
import com.dunamis.dunamisapi.repository.ClienteRepository;
import com.dunamis.dunamisapi.repository.ReservacionRepository;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionException;

@RestController
@CrossOrigin(origins = {"http://localhost:5173/", "http://localhost:3000/", "http://localhost:5174/"})
public class ReservacionController {

    @Autowired
    private ReservacionRepository reservacionRepository;

    @Autowired
    private AutomovilRepository automovilRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/reservacion")
    public ResponseEntity<Reservacion> nuevaReservacion(@RequestBody Map<String, Object> reservacionDatos){
        try{
            String idAutmovil = (String) reservacionDatos.get("placa");
            String idCliente = (String) reservacionDatos.get("idCliente");
            Automovil automovil = automovilRepository.findById(idAutmovil).orElseThrow(() -> new IllegalArgumentException("El automovil con la placa numero " + idAutmovil + " no existe"));
            Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new IllegalArgumentException("El cliente con el id " + idCliente + " no existe"));
            String fechaFinString = (String) reservacionDatos.get("fechaFin");
            String fechaInicioString = (String) reservacionDatos.get("fechaInicio");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechafinDate = sdf.parse(fechaFinString);
            Date fechaInicioDate = sdf.parse(fechaInicioString);


            automovil.setAutomovilActivo(false);
            automovilRepository.save(automovil);

            Reservacion reservacion = new Reservacion();
            reservacion.setFechaFin(fechafinDate);
            reservacion.setFechaInicio(fechaInicioDate);
            reservacion.setKmFinales((int) reservacionDatos.get("kmFinales"));
            reservacion.setKmIniciales((int) reservacionDatos.get("kmIniciales"));
            reservacion.setReservacionActivo((boolean) reservacionDatos.get("reservacionActivo"));
            reservacion.setAutomovil(automovil);
            reservacion.setCliente(cliente);

            System.out.println("Saving: " + reservacion.toString());
            Reservacion savedReservacion = reservacionRepository.save(reservacion);
            return ResponseEntity.ok(savedReservacion);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (ConstraintViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error de validacion ", e);
        }
    }


    @GetMapping("/reservaciones")
    List<Reservacion> reservacionesTodas(){return reservacionRepository.findAll();}

    @GetMapping("/reservacion/{id}")
    Reservacion obtenerReservacionPorId(@PathVariable int id){
        return reservacionRepository.findById(id).orElseThrow(()-> new ReservacionNotFoundException(id));
    }

    @GetMapping("/reservacionesDTO")
    public List<ReservacionDTO> obtenerTodosLasReservacionesDTO() {
        List<Reservacion> reservaciones = reservacionRepository.findAll();
        List<ReservacionDTO> reservasDTOs = new ArrayList<>();

        for (Reservacion reservacion : reservaciones) {
            ReservacionDTO dto = new ReservacionDTO();
            dto.setIdReservacion(reservacion.getIdReservacion());
            dto.setFechaFin(reservacion.getFechaFin());
            dto.setFechaInicio(reservacion.getFechaInicio());
            dto.setKmFinales(reservacion.getKmFinales());
            dto.setKmIniciales(reservacion.getKmIniciales());
            dto.setReservacionActivo(reservacion.isReservacionActivo());
            dto.setAutoPlaca(reservacion.getAutomovil().getPlaca());
            dto.setIdCliente(reservacion.getCliente().getIdCliente());
            reservasDTOs.add(dto);
        }

        return reservasDTOs;
    }

    @PutMapping("/reservacion/{id}")
    Reservacion actualizarReservacion(@RequestBody Reservacion newReservacion, @PathVariable int id){
        return  reservacionRepository.findById(id).map(reserva ->{
            reserva.setFechaInicio(newReservacion.getFechaInicio());
            reserva.setFechaFin(newReservacion.getFechaFin());
            reserva.setKmFinales(newReservacion.getKmFinales());
            reserva.setKmIniciales(newReservacion.getKmIniciales());
            reserva.setReservacionActivo(newReservacion.isReservacionActivo());
            return reservacionRepository.save(reserva);
        }).orElseThrow(()-> new ReservacionNotFoundException(id));
    }

    @DeleteMapping("/reservacion/{id}")
    String deleteReservacion(@PathVariable int id){
        if(!reservacionRepository.existsById(id)){
            throw new ReservacionNotFoundException(id);
        }

        try {
            reservacionRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "No se puede eliminar la reserva", e);
        }

        return "La reserva con el id " + id + " ha sido eliminada satisfactoriamente";
    }
}
