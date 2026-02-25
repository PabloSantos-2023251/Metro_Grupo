package com.Metro.org.controller;

import com.Metro.org.entity.Estaciones;
import com.Metro.org.service.EstacionesService;
import jakarta.validation.Valid;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estaciones")
public class EstacionesController {

    private final EstacionesService estacionesService;

    public EstacionesController (EstacionesService estacionesService){this.estacionesService = estacionesService;

    }

    @GetMapping
    public List<Estaciones> getAllEstaciones(){return estacionesService.getAllEstaciones();

    }

    @PostMapping
    public ResponseEntity<Object> createEstaciones(@Valid @RequestBody Estaciones estaciones){
        try{
            Estaciones createEstaciones = estacionesService.saveEstaciones(estaciones);
            return new ResponseEntity<>(createEstaciones,HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEstaciones (@PathVariable Integer id){
        try{
            estacionesService.deleteEstaciones(id);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estaciones> updateEstaciones(@PathVariable Integer id, @RequestBody Estaciones estaciones){
        Estaciones EstacionesActualizar = estacionesService.updateEstaciones(id, estaciones);
        return ResponseEntity.ok(EstacionesActualizar);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEstacionesId(@PathVariable Integer id){
        try{
            Estaciones estaciones = estacionesService.getEstacionesById(id);
            return ResponseEntity.ok(estaciones);
        }catch (ObjectNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
