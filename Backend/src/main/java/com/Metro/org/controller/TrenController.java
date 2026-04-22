package com.Metro.org.controller;

import com.Metro.org.entity.Trenes;
import com.Metro.org.service.TrenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/trenes")
public class TrenController {

    private final TrenService trenService;

    public TrenController(TrenService trenService){
        this.trenService = trenService;
    }

    // 📌 Vista principal
    @GetMapping
    public String verPagina(Model model){
        model.addAttribute("trenes", trenService.getAllTren());
        model.addAttribute("tren", new Trenes());
        return "Trenes";
    }

    // 📌 Guardar (formulario)
    @PostMapping("/guardar")
    public String guardarTren(@Valid @ModelAttribute("tren") Trenes tren){
        trenService.saveTren(tren);
        return "redirect:/trenes";
    }

    // 📌 Editar (cargar datos)
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model){
        model.addAttribute("tren", trenService.getTrenById(id));
        model.addAttribute("trenes", trenService.getAllTren());
        return "Trenes";
    }

    // 📌 Eliminar (WEB)
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id){
        trenService.deleteTren(id);
        return "redirect:/trenes";
    }

    // 📌 API REST - Crear
    @ResponseBody
    @PostMapping("/api")
    public ResponseEntity<?> createTren(@Valid @RequestBody Trenes tren){
        try {
            return new ResponseEntity<>(trenService.saveTren(tren), HttpStatus.CREATED);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 📌 API REST - Actualizar
    @ResponseBody
    @PutMapping("/api/{id}")
    public ResponseEntity<?> updateTren(@PathVariable Integer id,
                                        @RequestBody Trenes tren){
        try {
            return ResponseEntity.ok(trenService.updateTren(id, tren));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // 📌 API REST - Eliminar
    @ResponseBody
    @DeleteMapping("/api/{id}")
    public ResponseEntity<?> deleteTrenApi(@PathVariable Integer id){
        try {
            trenService.deleteTren(id);
            return ResponseEntity.ok("Tren eliminado correctamente");
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}