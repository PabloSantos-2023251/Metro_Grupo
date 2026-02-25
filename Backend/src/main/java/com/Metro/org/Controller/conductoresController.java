package com.Metro.org.Controller;

import com.Metro.org.Entity.conductores;
import com.Metro.org.Service.conductoresService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/Conductores")

public class conductoresController {
    private final conductoresService conductoresService;
    public conductoresController(conductoresService ConductoresService){
        this.conductoresService = ConductoresService;
    }

    @GetMapping
    public List<conductores> getAllConductores(){return conductoresService.getAllConductores();}

    @PostMapping
    public ResponseEntity<Object> createConductores(@Valid @RequestBody conductores conductores){
        try{
            conductores createdConductores = conductoresService.saveConductores(conductores);
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
    public ResponseEntity<?> updateConductores(@PathVariable Integer id, @RequestBody conductores conductores) {

        try {
            conductores actualizado = conductoresService.updateConductores(id, conductores);
            return ResponseEntity.ok(actualizado);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
}