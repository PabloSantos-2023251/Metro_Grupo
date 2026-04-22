package com.Metro.org.controller;

import com.Metro.org.entity.Linea;
import com.Metro.org.service.LineaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/linea")
public class LineaController {

    private final LineaService lineaService;

    public LineaController(LineaService lineaService){
        this.lineaService = lineaService;
    }

    @GetMapping
    public String verPagina(Model model){
        List<Linea> lista = lineaService.getAllLinea();
        model.addAttribute("lineas", lista);
        return "Linea";
    }

    @ResponseBody
    @PostMapping("/api")
    public ResponseEntity<Object> createLinea(@Valid @RequestBody Linea linea){
        try{
            Linea creada = lineaService.saveLinea(linea);
            return new ResponseEntity<>(creada, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ResponseBody
    @DeleteMapping("/api/{id}")
    public ResponseEntity<?> deleteLinea(@PathVariable Integer id){
        try{
            lineaService.deleteLinea(id);
            return ResponseEntity.ok("Línea eliminada correctamente");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ResponseBody
    @PutMapping("/api/{id}")
    public ResponseEntity<?> updateLinea(@PathVariable Integer id, @RequestBody Linea linea){
        try{
            Linea actualizada = lineaService.updateLinea(id, linea);
            return ResponseEntity.ok(actualizada);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}