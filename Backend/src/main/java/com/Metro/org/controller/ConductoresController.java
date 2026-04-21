package com.Metro.org.controller;

import com.Metro.org.entity.Conductores;
import com.Metro.org.service.ConductoresService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/conductores")
public class ConductoresController {

    private final ConductoresService conductoresService;

    public ConductoresController(ConductoresService conductoresService){
        this.conductoresService = conductoresService;
    }

    @GetMapping
    public String verPagina(Model model){
        List<Conductores> lista = conductoresService.getAllConductores();
        model.addAttribute("conductores", lista);
        return "Conductores";
    }

    @ResponseBody
    @PostMapping("/api")
    public ResponseEntity<Object> createConductores(@Valid @RequestBody Conductores conductores){
        try{
            Conductores createdConductores = conductoresService.saveConductores(conductores);
            return new ResponseEntity<>(createdConductores, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ResponseBody
    @DeleteMapping("/api/{id}")
    public ResponseEntity<?> deleteConductores(@PathVariable Integer id){
        try {
            conductoresService.deleteConductores(id);
            return ResponseEntity.ok("Conductor eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ResponseBody
    @PutMapping("/api/{id}")
    public ResponseEntity<?> updateConductores(@PathVariable Integer id, @RequestBody Conductores conductores) {
        try {
            Conductores actualizado = conductoresService.updateConductores(id, conductores);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}