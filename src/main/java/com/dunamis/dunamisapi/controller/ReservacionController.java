package com.dunamis.dunamisapi.controller;

import com.dunamis.dunamisapi.exception.ReservacionNotFoundException;
import com.dunamis.dunamisapi.model.Automovil;
import com.dunamis.dunamisapi.model.Cliente;
import com.dunamis.dunamisapi.model.Reservacion;
import com.dunamis.dunamisapi.repository.AutomovilRepository;
import com.dunamis.dunamisapi.repository.ClienteRepository;
import com.dunamis.dunamisapi.repository.ReservacionRepository;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
            String idAutmovil = (String) reservacionDatos.get("automovil_placa");
            String idCliente =  (String) reservacionDatos.get("cliente_id_cliente");
            Automovil automovil = automovilRepository.findById(idAutmovil).orElseThrow(null);
            Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(null);
            String fechaFinString = (String) reservacionDatos.get("fecha_fin");
            String fechaInicioString = (String) reservacionDatos.get("fecha_inicio");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechafinDate = sdf.parse(fechaFinString);
            Date fechaInicioDate = sdf.parse(fechaInicioString);
            Reservacion reservacion = new Reservacion();

            if(automovil != null && cliente != null){
                reservacion.setIdReservacion((int) reservacionDatos.get("id_reservacion"));
                reservacion.setFechaFin(fechafinDate);
                reservacion.setFechaInicio(fechaInicioDate);
                reservacion.setKmFinales((int) reservacionDatos.get("km_finales"));
                reservacion.setKmIniciales((int) reservacionDatos.get("km_iniciales"));
                reservacion.setAutomovil(automovil);
                reservacion.setCliente(cliente);
            }else{
                throw new IllegalArgumentException("El automovil con la placa numero " + idAutmovil + " o el cliente con el id " + idCliente + " no existe");
            }
            System.out.println("Saving: " + reservacion.toString());
            Reservacion savedReservacion = reservacionRepository.save(reservacion);
            return ResponseEntity.ok(savedReservacion);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }catch (ConstraintViolationException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error de validacion ", e);
        }
    }

    @GetMapping("/reservacion")
    List<Reservacion> reservacionesTodas(){return reservacionRepository.findAll();}

    @GetMapping("/reservacion/{id}")
    Reservacion obtenerReservacionPorId(@PathVariable int id){
        return reservacionRepository.findById(id).orElseThrow(()-> new ReservacionNotFoundException(id));
    }

    @PutMapping("/reservacion/{id}")
    Reservacion actualizarReservacion(@RequestBody Reservacion newReservacion, @PathVariable int id){
        return  reservacionRepository.findById(id).map(reserva ->{
            reserva.setFechaInicio(newReservacion.getFechaInicio());
            reserva.setFechaFin(newReservacion.getFechaFin());
            reserva.setKmFinales(newReservacion.getKmFinales());
            reserva.setKmIniciales(newReservacion.getKmIniciales());
            return reservacionRepository.save(reserva);
        }).orElseThrow(()-> new ReservacionNotFoundException(id));
    }

    @DeleteMapping("/reservacion/{id}")
    String deleteReservacion(@PathVariable int id){
        if(!reservacionRepository.existsById(id)){
            throw new ReservacionNotFoundException(id);
        }
        reservacionRepository.deleteById(id);
        return "La reserva con el id " + id + " ha sido eliminada satisfactoriamente";
    }
}
