package com.Metro.org.controller;

import com.Metro.org.entity.Linea;
import com.Metro.org.service.LineaService;
import jakarta.validation.Valid;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.HashMap;
import java.util.Map;

import java.util.List;

@RestController
@RequestMapping ("/api/linea")
public class LineaController {

    private final LineaService lienaService;

    public LineaController (LineaService lineaService){this.lienaService = lineaService;

    }

    @GetMapping
    public List<Linea> getAllLinea(){return lienaService .getAllLinea();

    }

    @PostMapping
    public ResponseEntity<Object> createLinea(@Valid @RequestBody Linea linea) {
        try {
            Linea createLinea = lienaService.saveLinea(linea);
            return new ResponseEntity<>(createLinea, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteLinea (@PathVariable Integer id){
        try{
            lienaService.deleteLinea(id);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Linea> updateLinea(
            @PathVariable Integer id,
            @Valid @RequestBody Linea linea) {

        Linea lineaActualizar = lienaService.updateLinea(id, linea);
        return ResponseEntity.ok(lineaActualizar);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getLineaId(@PathVariable Integer id){
        try{
            Linea linea = lienaService.getLineaById(id);
            return ResponseEntity.ok(linea);
        }catch (ObjectNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarErroresValidacion(
            MethodArgumentNotValidException ex) {

        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errores);
    }


}