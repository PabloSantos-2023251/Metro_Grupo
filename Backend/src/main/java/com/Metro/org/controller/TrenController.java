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

    // 📌 Vista principal (Thymeleaf)
    @GetMapping
    public String verPagina(Model model){

        List<Trenes> lista = trenService.getAllTren();

        model.addAttribute("trenes", lista);   // lista para tabla
        model.addAttribute("tren", new Trenes()); // objeto para formulario 👈 IMPORTANTE

        return "Trenes";
    }

    // 📌 Guardar desde formulario HTML (Thymeleaf)
    @PostMapping("/guardar")
    public String guardarTren(@Valid @ModelAttribute("tren") Trenes tren){
        trenService.saveTren(tren);
        return "redirect:/trenes";
    }

    // 📌 API REST - Crear
    @ResponseBody
    @PostMapping("/api")
    public ResponseEntity<Object> createTren(@Valid @RequestBody Trenes tren){
        try {
            Trenes createdTren = trenService.saveTren(tren);
            return new ResponseEntity<>(createdTren, HttpStatus.CREATED);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 📌 API REST - Eliminar
    @ResponseBody
    @DeleteMapping("/api/{id}")
    public ResponseEntity<?> deleteTren(@PathVariable Integer id){
        try {
            trenService.deleteTren(id);
            return ResponseEntity.ok("Tren eliminado correctamente");
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    // 📌 API REST - Actualizar
    @ResponseBody
    @PutMapping("/api/{id}")
    public ResponseEntity<?> updateTren(@PathVariable Integer id,
                                        @RequestBody Trenes tren){
        try {
            Trenes actualizado = trenService.updateTren(id, tren);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}