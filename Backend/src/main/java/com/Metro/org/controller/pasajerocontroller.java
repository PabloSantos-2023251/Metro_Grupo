package com.Metro.org.controller;

import com.Metro.org.entity.pasajero;
import com.Metro.org.service.pasajeroservice;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/pasajeros")

public class pasajerocontroller {
    private final pasajeroservice pasajeroService;

    public pasajerocontroller(pasajeroservice pasajeroService) {this.pasajeroService = pasajeroService;}

    @GetMapping
    public List<pasajero> getAllPasajeros(){return pasajeroService.getAllPasajeros();}

    @GetMapping ("/{id}")
    public  ResponseEntity<Object>  getAllPasajeroById(Integer id){
        try {
            pasajero pasajeroSolicitado = pasajeroService.getPasajeroById(id);
            return new ResponseEntity<>(pasajeroSolicitado, HttpStatus.ACCEPTED);
        }catch (IllegalArgumentException e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> createPasajero(@Valid @RequestBody pasajero pasajero){
        try {
            pasajero createdPasajero = pasajeroService.savePasajero(pasajero);
            return  new ResponseEntity<>(createdPasajero, HttpStatus.CREATED);
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Object> updatePasajero(
            @PathVariable Integer id,
            @Valid @RequestBody pasajero pasajero) {

        try{
            pasajeroService.updatePasajero(id, pasajero);
            return ResponseEntity.ok("Pasajero actualizado correctamente");
        }catch (RuntimeException e){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo actualizar al Pasajero");
        }

    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Object> deletePasajero(@PathVariable Integer id) {
        try {
            pasajeroService.deletePasajero(id);
            return ResponseEntity.ok("Pasajero eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pasajero no encontrado");
        }
    }

}
