package com.dunamis.dunamisapi.controller;

import com.dunamis.dunamisapi.dto.AutomovilDTO;
import com.dunamis.dunamisapi.exception.AutomovilNotFoundException;
import com.dunamis.dunamisapi.model.Automovil;
import com.dunamis.dunamisapi.model.Segmento;
import com.dunamis.dunamisapi.repository.AutomovilRepository;
import com.dunamis.dunamisapi.repository.SegmentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
public class AutomovilController {

    @Autowired
    private AutomovilRepository automovilRepository;

    @Autowired
    private SegmentoRepository segmentoRepository;

    @PostMapping("/automovil")
    public ResponseEntity<Automovil> newAutomovil(@RequestBody Map<String, Object> automovilDatos){
        try{
            Automovil auto = new Automovil();
            int idSegemento = (int) automovilDatos.get("idSegmento");
            Segmento segmento = segmentoRepository.getById(idSegemento);

            if(segmento != null){
                auto.setPlaca((String) automovilDatos.get("placa"));
                auto.setTransmision((String) automovilDatos.get("transmision"));
                auto.setAnno((int) automovilDatos.get("anno"));
                auto.setAutomovilActivo((boolean) automovilDatos.get("automovilActivo"));
                auto.setCabina((String) automovilDatos.get("cabina"));
                auto.setCarroceria((String) automovilDatos.get("carroceria"));
                auto.setColor((String) automovilDatos.get("color"));
                auto.setCombustible((String) automovilDatos.get("combustible"));
                auto.setCosto((double) automovilDatos.get("costo"));
                auto.setEstilo((String) automovilDatos.get("estilo"));
                auto.setMarca((String) automovilDatos.get("marca"));
                auto.setModelo((String) automovilDatos.get("modelo"));
                auto.setTraccion((String) automovilDatos.get("traccion"));
                auto.setSegmento(segmento);
            }else{
                throw new IllegalArgumentException("El segmento con el id " + idSegemento + " no existe");
            }
            System.out.println("Saving: " + automovilDatos.toString());
            Automovil saeAutomovil = automovilRepository.save(auto);
            return ResponseEntity.ok(saeAutomovil);
        }catch (ConstraintViolationException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error de validacion ", e);
        }
    }

    @GetMapping("/automoviles")
    List<Automovil> automovilesTodos(){return automovilRepository.findAll();}

    @GetMapping("/automovilesActivos")
    List<Automovil> automovilesActivos() {
        return automovilRepository.findByAutomovilActivoTrue();
    }

    @GetMapping("/automovilDispoDTO")
    public List<AutomovilDTO> obtenerTodosLosAutomovilesDisponiblesDTO() {
        List<Automovil> automoviles = automovilRepository.findByAutomovilActivoTrue();
        List<AutomovilDTO> automovilDTOs = new ArrayList<>();

        for (Automovil automovil : automoviles) {
            AutomovilDTO dto = new AutomovilDTO();
            dto.setPlaca(automovil.getPlaca());
            dto.setMarca(automovil.getMarca());
            dto.setModelo(automovil.getModelo());
            dto.setAnno(automovil.getAnno());
            dto.setColor(automovil.getColor());
            dto.setEstilo(automovil.getEstilo());
            dto.setCarroceria(automovil.getCarroceria());
            dto.setCombustible(automovil.getCombustible());
            dto.setCabina(automovil.getCabina());
            dto.setTraccion(automovil.getTraccion());
            dto.setTransmision(automovil.getTransmision());
            dto.setCosto(automovil.getCosto());
            dto.setAutomovilActivo(automovil.isAutomovilActivo());
            dto.setSegmentoNombre(automovil.getSegmento().getNombre());
            automovilDTOs.add(dto);
        }

        return automovilDTOs;
    }

    @GetMapping("/automovil/{id}")
    Automovil obtenerAutomovilPorId(@PathVariable String id){
        return  automovilRepository.findById(id).orElseThrow(()-> new AutomovilNotFoundException(id));
    }

    @GetMapping("/automovilDTO/{id}")
    public AutomovilDTO obtenerAutomovilDTOPorId(@PathVariable String id) {
        Automovil automovil = automovilRepository.findById(id)
                .orElseThrow(() -> new AutomovilNotFoundException(id));

        AutomovilDTO dto = new AutomovilDTO();
        dto.setPlaca(automovil.getPlaca());
        dto.setMarca(automovil.getMarca());
        dto.setModelo(automovil.getModelo());
        dto.setAnno(automovil.getAnno());
        dto.setColor(automovil.getColor());
        dto.setEstilo(automovil.getEstilo());
        dto.setCarroceria(automovil.getCarroceria());
        dto.setCombustible(automovil.getCombustible());
        dto.setCabina(automovil.getCabina());
        dto.setTraccion(automovil.getTraccion());
        dto.setTransmision(automovil.getTransmision());
        dto.setCosto(automovil.getCosto());
        dto.setAutomovilActivo(automovil.isAutomovilActivo());
        dto.setSegmentoNombre(automovil.getSegmento().getNombre());

        return dto;
    }

    @GetMapping("/automovilesDTO")
    public List<AutomovilDTO> obtenerTodosLosAutomovilesDTO() {
        List<Automovil> automoviles = automovilRepository.findAll();
        List<AutomovilDTO> automovilDTOs = new ArrayList<>();

        for (Automovil automovil : automoviles) {
            AutomovilDTO dto = new AutomovilDTO();
            dto.setPlaca(automovil.getPlaca());
            dto.setMarca(automovil.getMarca());
            dto.setModelo(automovil.getModelo());
            dto.setAnno(automovil.getAnno());
            dto.setColor(automovil.getColor());
            dto.setEstilo(automovil.getEstilo());
            dto.setCarroceria(automovil.getCarroceria());
            dto.setCombustible(automovil.getCombustible());
            dto.setCabina(automovil.getCabina());
            dto.setTraccion(automovil.getTraccion());
            dto.setTransmision(automovil.getTransmision());
            dto.setCosto(automovil.getCosto());
            dto.setAutomovilActivo(automovil.isAutomovilActivo());
            dto.setSegmentoNombre(automovil.getSegmento().getNombre());
            automovilDTOs.add(dto);
        }

        return automovilDTOs;
    }

    //Consultas
    @GetMapping("/automovilPorPlaca/{placa}")
    public ResponseEntity<Automovil> obtenerAutomovilPorPlaca(@PathVariable String placa){
        Automovil automovil = automovilRepository.findByPlaca(placa);
        if (automovil == null){
          throw new AutomovilNotFoundException(placa);
        }
        return ResponseEntity.ok(automovil);
    }

    @GetMapping("/automovilesPorTipo/{tipo}")
    public List<Automovil> obtenerAutomovilesPorTipo(@PathVariable String tipo){
        return automovilRepository.findByTipo(tipo);
    }

    @GetMapping("/tarifaBasePorTipo/{tipo}")
    public ResponseEntity<Double> obtenerTarifaBasePorTipo(@PathVariable String tipo){
        Double tarifaBase = automovilRepository.findTarifaBaseByTipo(tipo);
       if (tarifaBase == null){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarifa base no disponible");

        }
        return ResponseEntity.ok(tarifaBase);
    }

    @GetMapping("/tarifaPorKilometroPorTipo/{tipo}")
    public ResponseEntity<Double> obtenerTarifaPorKilometroPorTipo(@PathVariable String tipo){
        Double tarifaPorKilometro = automovilRepository.findTarifaPorKilometroByTipo(tipo);
        if (tarifaPorKilometro == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarifa por kilómetro no disponible");
        }
        return ResponseEntity.ok(tarifaPorKilometro);
    }


    //

    @PutMapping("/automovil/{id}")
    Automovil actualizarAutomovil(@RequestBody Automovil automovil, @PathVariable String id){
        return automovilRepository.findById(id).map(auto ->{
            auto.setAutomovilActivo((boolean) automovil.isAutomovilActivo());
            auto.setColor((String) automovil.getColor());
            auto.setCosto((Double) automovil.getCosto());
            return automovilRepository.save(auto);
        }).orElseThrow(()-> new AutomovilNotFoundException(id));
    }

    @DeleteMapping("/automovil/{id}")
    String deleteAutomovil(@PathVariable String id){
        if(!automovilRepository.existsById(id)){
            throw new AutomovilNotFoundException(id);
        }

        try {
            automovilRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "No se puede eliminar el automóvil porque tiene reservas asociadas", e);
        }

        return "El automóvil con el id " + id + " ha sido eliminado satisfactoriamente";
    }


}
