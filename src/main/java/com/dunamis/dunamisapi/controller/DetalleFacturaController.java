package com.dunamis.dunamisapi.controller;

import com.dunamis.dunamisapi.dto.DetalleFacturaDTO;
import com.dunamis.dunamisapi.dto.ReservacionDTO;
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
import java.util.ArrayList;
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
            int idFactura = (int) detalleFacturaDatos.get("factura");
            Factura factura = facturaRepository.getById(idFactura);
            int idReserva = (int) detalleFacturaDatos.get("reservacion");
            Reservacion reserva = reservacionRepository.getById(idReserva);

            if(factura != null && reserva != null){
                detalleFactura.setCantidadDias((int) detalleFacturaDatos.get("cantidadDias"));
                detalleFactura.setCantidadKmRecorridos((int) detalleFacturaDatos.get("cantidadKmRecorridos"));
                detalleFactura.setPrecioKmAutomovil((Double) detalleFacturaDatos.get("precioKmAutomovil"));
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

    @GetMapping("/detalle-facturaDTO")
    public List<DetalleFacturaDTO> detalleFacturasTodasDTO() {
        List<DetalleFactura> detallefactura = detalleFacturaRepository.findAll();
        List<DetalleFacturaDTO> detallefacturaDTOs = new ArrayList<>();

        for (DetalleFactura detalleFac : detallefactura) {
            DetalleFacturaDTO dto = new DetalleFacturaDTO();
            dto.setIdDetalleFactura(detalleFac.getIdDetalleFactura());
            dto.setSubtotal(detalleFac.getSubtotal());
            dto.setPrecioKmAutomovil(detalleFac.getPrecioKmAutomovil());
            dto.setCantidadDias(detalleFac.getCantidadDias());

            dto.setCantidadKmRecorridos((int) detalleFac.getCantidadKmRecorridos());
            dto.setFacturaFecha(detalleFac.getFactura().getFecha().toString());
            dto.setReservacionId(String.valueOf(detalleFac.getReservacion().getIdReservacion()));

            detallefacturaDTOs.add(dto);
        }

        return detallefacturaDTOs;
    }


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
    public String deleteDetalleFactura(@PathVariable int id) {
        if (!detalleFacturaRepository.existsById(id)) {
            throw new DetalleFacturaNotFoundException(id);
        }

        DetalleFactura detalleFactura = detalleFacturaRepository.findById(id)
                .orElseThrow(() -> new DetalleFacturaNotFoundException(id));

        Factura factura = detalleFactura.getFactura();

        // Eliminar el detalle de factura
        detalleFacturaRepository.deleteById(id);

        // Verificar si aún quedan detalles asociados a la factura
        if (detalleFacturaRepository.countByFactura(factura) == 0) {
            // Eliminar la factura si ya no tiene detalles asociados
            facturaRepository.delete(factura);
        }

        return "El Detalle de Factura con el id " + id + " y su factura asociada han sido eliminados satisfactoriamente";
    }

}
