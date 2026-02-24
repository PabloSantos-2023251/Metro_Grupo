package com.Metro.org.Controller;


import com.Metro.org.Entity.Tren;
import com.Metro.org.Service.TrenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/Tren")

public class TrenController {
    private final TrenService trenService;
    public TrenController (TrenService TrenService){
        this.trenService = TrenService;
    }

    @GetMapping
    public List<Tren> getAllTren(){return trenService.getAllTren();}

    @PostMapping
    public ResponseEntity<Object> createTren(@Valid @RequestBody Tren tren){
        try{
            Tren createdTren = trenService.saveTren(tren);
            return new ResponseEntity<>(createdTren, HttpStatus.CREATED);
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
    public ResponseEntity<?> updateTren(@PathVariable Integer id, @RequestBody Tren tren) {

        try {
            Tren actualizado = trenService.updateTren(id, tren);
            return ResponseEntity.ok(actualizado);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
}