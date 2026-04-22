package com.Metro.org.controller;

import com.Metro.org.entity.Estaciones;
import com.Metro.org.service.EstacionesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/estaciones")
public class EstacionesController {

    private final EstacionesService estacionesService;

    public EstacionesController(EstacionesService estacionesService){
        this.estacionesService = estacionesService;
    }

    @GetMapping
    public String verPagina(Model model){
        List<Estaciones> lista = estacionesService.getAllEstaciones();
        model.addAttribute("estaciones", lista);
        return "estacion";
    }

    @ResponseBody
    @PostMapping("/api")
    public ResponseEntity<Object> createEstaciones(@Valid @RequestBody Estaciones estaciones){
        try{
            Estaciones creada = estacionesService.saveEstaciones(estaciones);
            return new ResponseEntity<>(creada, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ResponseBody
    @DeleteMapping("/api/{id}")
    public ResponseEntity<?> deleteEstaciones(@PathVariable Integer id){
        try{
            estacionesService.deleteEstaciones(id);
            return ResponseEntity.ok("Estación eliminada correctamente");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ResponseBody
    @PutMapping("/api/{id}")
    public ResponseEntity<?> updateEstaciones(@PathVariable Integer id, @RequestBody Estaciones estaciones){
        try{
            Estaciones actualizada = estacionesService.updateEstaciones(id, estaciones);
            return ResponseEntity.ok(actualizada);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
