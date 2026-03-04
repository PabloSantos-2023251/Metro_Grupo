package com.Metro.org.controller;


import com.Metro.org.entity.trenes;
import com.Metro.org.service.TrenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/Tren")

public class TrenController {
    private final TrenService trenService;
    public TrenController(TrenService TrenService){
        this.trenService = TrenService;
    }

    @GetMapping
    public List<trenes> getAllTren(){return trenService.getAllTren();}

    @PostMapping
    public ResponseEntity<Object> createTren(@Valid @RequestBody trenes trenes){
        try{
            trenes createdTrenes = trenService.saveTren(trenes);
            return new ResponseEntity<>(createdTrenes, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTren(@PathVariable Integer id){
        try {
            trenService.deleteTren(id);
            return ResponseEntity.ok("Tren Eliminado Correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTren(@PathVariable Integer id, @RequestBody trenes trenes) {

        try {
            trenes actualizado = trenService.updateTren(id, trenes);
            return ResponseEntity.ok(actualizado);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
}