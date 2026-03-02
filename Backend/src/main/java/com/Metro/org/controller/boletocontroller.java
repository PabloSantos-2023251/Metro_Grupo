package com.Metro.org.controller;

import com.Metro.org.entity.boleto;
import com.Metro.org.service.boletoservice;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/boletos")

public class boletocontroller {
    private final boletoservice boletoService;

    public boletocontroller(boletoservice boletoService) {this.boletoService = boletoService;}

    @GetMapping
    public List<boleto> getAllBoletos(){return boletoService.getAllBoletos();}

    @GetMapping ("/{id}")
    public  ResponseEntity<Object>  getAllBoletoById(Integer id){
        try {
            boleto boletoSolicitado = boletoService.getBoletoById(id);
            return new ResponseEntity<>(boletoSolicitado, HttpStatus.ACCEPTED);
        }catch (IllegalArgumentException e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> createPasajero(@Valid @RequestBody boleto boleto){
        try {
            boleto createdBoleto = boletoService.saveBoleto(boleto);

            return  new ResponseEntity<>(createdBoleto, HttpStatus.CREATED);
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Object> updateBoleto(
            @PathVariable Integer id,
            @Valid @RequestBody boleto boleto) {

        try{
            boletoService.updateBoleto(id, boleto);
            return ResponseEntity.ok("Boleto actualizado correctamente");
        }catch (RuntimeException e){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo actualizar el boleto");
        }

    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Object> deleteBoleto(@PathVariable Integer id) {
        try {
            boletoService.deleteBoleto(id);
            return ResponseEntity.ok("Boleto anulado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Boleto no encontrado");
        }
    }

}
