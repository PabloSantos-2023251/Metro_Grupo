package com.Metro.org.controller;

import com.Metro.org.entity.Conductores;
import com.Metro.org.service.ConductoresService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/Conductores")

public class ConductoresController {
    private final ConductoresService conductoresService;
    public ConductoresController(ConductoresService ConductoresService){
        this.conductoresService = ConductoresService;
    }

    @GetMapping
    public List<Conductores> getAllConductores(){return conductoresService.getAllConductores();}

    @PostMapping
    public ResponseEntity<Object> createConductores(@Valid @RequestBody Conductores conductores){
        try{
            Conductores createdConductores = conductoresService.saveConductores(conductores);
            return new ResponseEntity<>(createdConductores, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteConductores(@PathVariable Integer id){
        try {
            conductoresService.deleteConductores(id);
            return ResponseEntity.ok("Conductores Eliminado Correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateConductores(@PathVariable Integer id, @RequestBody Conductores conductores) {

        try {
            Conductores actualizado = conductoresService.updateConductores(id, conductores);
            return ResponseEntity.ok(actualizado);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
}