package com.Metro.org.controller;

import com.Metro.org.entity.Boleto;
import com.Metro.org.service.BoletoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boletos")
public class BoletoController {

    private final BoletoService boletoService;

    public BoletoController(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    @GetMapping
    public List<Boleto> getAllBoletos() {
        return boletoService.getAllBoletos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBoletoById(@PathVariable Integer id) {
        try {
            Boleto boleto = boletoService.getBoletoById(id);
            return ResponseEntity.ok(boleto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> createBoleto(@Valid @RequestBody Boleto boleto) {
        try {
            Boleto created = boletoService.saveBoleto(boleto);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBoleto(@PathVariable Integer id, @Valid @RequestBody Boleto boleto) {
        try {
            boletoService.updateBoleto(id, boleto);
            return ResponseEntity.ok("Boleto actualizado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBoleto(@PathVariable Integer id) {
        try {
            boletoService.deleteBoleto(id);
            return ResponseEntity.ok("Boleto anulado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}