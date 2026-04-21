package com.Metro.org.controller;

import com.Metro.org.entity.Pasajero;
import com.Metro.org.service.PasajeroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pasajeros")
public class PasajeroController {

    private final PasajeroService pasajeroService;

    public PasajeroController(PasajeroService pasajeroService) {
        this.pasajeroService = pasajeroService;
    }

    @GetMapping
    public List<Pasajero> getAllPasajeros() {
        return pasajeroService.getAllPasajeros();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPasajeroById(@PathVariable Integer id) { // Corregido @PathVariable
        try {
            Pasajero pasajero = pasajeroService.getPasajeroById(id);
            return ResponseEntity.ok(pasajero);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> createPasajero(@Valid @RequestBody Pasajero pasajero) {
        try {
            Pasajero created = pasajeroService.savePasajero(pasajero);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePasajero(@PathVariable Integer id, @Valid @RequestBody Pasajero pasajero) {
        try {
            pasajeroService.updatePasajero(id, pasajero);
            return ResponseEntity.ok("Pasajero actualizado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePasajero(@PathVariable Integer id) {
        try {
            pasajeroService.deletePasajero(id);
            return ResponseEntity.ok("Pasajero eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
