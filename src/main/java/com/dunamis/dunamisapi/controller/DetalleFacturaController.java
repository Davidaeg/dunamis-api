package com.dunamis.dunamisapi.controller;

import com.dunamis.dunamisapi.exception.DetalleFacturaNotFoundException;
import com.dunamis.dunamisapi.model.DetalleFactura;
import com.dunamis.dunamisapi.model.Factura;
import com.dunamis.dunamisapi.model.Reservacion;
import com.dunamis.dunamisapi.repository.DetalleFacturaRepository;
import com.dunamis.dunamisapi.repository.FacturaRepository;
import com.dunamis.dunamisapi.repository.ReservacionRepository;
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
public class DetalleFacturaController {

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    ReservacionRepository reservacionRepository;

    @PostMapping("/detalle-factura")
    public ResponseEntity<DetalleFactura> newDetalleFactura(@RequestBody Map<String, Object> detalleFacturaDatos){
        try{
            DetalleFactura detalleFactura = new DetalleFactura();
            int idFactura = (int) detalleFacturaDatos.get("factura_id_factura");
            Factura factura = facturaRepository.getById(idFactura);
            int idReserva = (int) detalleFacturaDatos.get("reservacion_id_reservacion");
            Reservacion reserva = reservacionRepository.getById(idReserva);

            if(factura != null && reserva != null){
                detalleFactura.setIdDetalleFactura((int) detalleFacturaDatos.get("id_detalle_factura"));
                detalleFactura.setCantidadDias((int) detalleFacturaDatos.get("cantidad_dias"));
                detalleFactura.setCantidadKmRecorridos((int) detalleFacturaDatos.get("cantidad_km_recorridos"));
                detalleFactura.setPrecioKmAutomovil((Double) detalleFacturaDatos.get("precio_km_automovil"));
                detalleFactura.setSubtotal((Double) detalleFacturaDatos.get("subtotal"));
                detalleFactura.setFactura(factura);
                detalleFactura.setReservacion(reserva);
            }else{
                throw new IllegalArgumentException("La Factura con el id " + idFactura + " o la Reservacion con el id " + idReserva + " no existe.");
            }
            System.out.printf("Saving: " + detalleFacturaDatos.toString());
            DetalleFactura savedDetalleFactura = detalleFacturaRepository.save(detalleFactura);
            return ResponseEntity.ok(savedDetalleFactura);
        }catch (ConstraintViolationException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error de validacion ", e);
        }
    }

    @GetMapping("/detalle-factura")
    List<DetalleFactura> detalleFacturasTodas(){return detalleFacturaRepository.findAll();}

    @GetMapping("/detalle-factura/{id}")
    DetalleFactura detalleFacturaPorId(@PathVariable int id){
        return detalleFacturaRepository.findById(id).orElseThrow(()-> new DetalleFacturaNotFoundException(id));
    }

    @PutMapping("/detalle-factura/{id}")
    DetalleFactura actualizarDetalleFactura(@RequestBody DetalleFactura newDetalleFactura, @PathVariable int id){
        return detalleFacturaRepository.findById(id).map(detalleFactura -> {
            detalleFactura.setCantidadDias((int) newDetalleFactura.getCantidadDias());
            detalleFactura.setCantidadKmRecorridos((int) newDetalleFactura.getCantidadKmRecorridos());
            detalleFactura.setPrecioKmAutomovil((Double) newDetalleFactura.getPrecioKmAutomovil());
            detalleFactura.setSubtotal((Double) newDetalleFactura.getSubtotal());
            return detalleFacturaRepository.save(detalleFactura);
        }).orElseThrow(()-> new DetalleFacturaNotFoundException(id));
    }

    @DeleteMapping("/detalle-factura/{id}")
    String deleteDetalleFactura(@PathVariable int id){
        if(!detalleFacturaRepository.existsById(id)){
            throw new DetalleFacturaNotFoundException(id);
        }
        detalleFacturaRepository.deleteById(id);
        return "El Detalle de Factura con el id " + id + " ha sido eliminado satisfactoriamente";
    }
}
