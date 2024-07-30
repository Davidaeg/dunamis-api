
package com.dunamis.dunamisapi.controller;
import com.dunamis.dunamisapi.exception.ReservacionNotFoundException;
import com.dunamis.dunamisapi.model.Reservacion;
import com.dunamis.dunamisapi.model.Automovil;
import com.dunamis.dunamisapi.model.Cliente;
import com.dunamis.dunamisapi.repository.ReservacionRepository;
import com.dunamis.dunamisapi.repository.AutomovilRepository;
import com.dunamis.dunamisapi.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Reservacion> nuevaReservacion(@RequestBody Map<String, Object> reservacionData) {
        try {
            Reservacion reservacion = new Reservacion();

            String automovilPlaca = (String) reservacionData.get("automovilPlaca");
            Automovil automovil = automovilRepository.findById(automovilPlaca).orElse(null);

            int clienteId = (int) reservacionData.get("clienteId");
            Cliente cliente = clienteRepository.findById(clienteId).orElse(null);

            if (automovil != null && cliente != null) {
                reservacion.setAutomovil(automovil);
                reservacion.setCliente(cliente);
                reservacion.setFechaInicio((Date) reservacionData.get("fechaInicio"));
                reservacion.setFechaFin((Date) reservacionData.get("fechaFin"));
                reservacion.setkmIniciales((int) reservacionData.get("kmIniciales"));
                reservacion.setKmFinales((int) reservacionData.get("kmFinales"));
            } else {
                return ResponseEntity.badRequest().build();
            }

            Reservacion savedReservacion = reservacionRepository.save(reservacion);
            return ResponseEntity.ok(savedReservacion);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/reservaciones")
    public List<Reservacion> getAllReservaciones() {
        return reservacionRepository.findAll();
    }

    @GetMapping("/reservacion/{id}")
    public Reservacion getReservacionById(@PathVariable int id) {
        return reservacionRepository.findById(id)
                .orElseThrow(() -> new ReservacionNotFoundException((long) id));
    }

    @PutMapping("/reservacion/{id}")
    public Reservacion updateReservacion(@RequestBody Reservacion newReservacion, @PathVariable int id) {
        return reservacionRepository.findById(id)
                .map(reservacion -> {
                    reservacion.setAutomovil(newReservacion.getAutomovil());
                    reservacion.setCliente(newReservacion.getCliente());
                    reservacion.setFechaInicio(newReservacion.getFechaInicio());
                    reservacion.setFechaFin(newReservacion.getFechaFin());
                    reservacion.setkmIniciales(newReservacion.getKmIniciales());
                    reservacion.setKmFinales(newReservacion.getKmFinales());
                    return reservacionRepository.save(reservacion);
                }).orElseThrow(() -> new ReservacionNotFoundException((long) id));
    }

    @DeleteMapping("/reservacion/{id}")
    public String deleteReservacion(@PathVariable int id) {
        if (!reservacionRepository.existsById(id)) {
            throw new ReservacionNotFoundException((long) id);
        }
        reservacionRepository.deleteById(id);
        return "Reservacion with id " + id + " has been deleted successfully.";
    }
}
