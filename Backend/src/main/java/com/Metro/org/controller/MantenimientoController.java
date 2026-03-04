package com.Metro.org.controller;

import com.Metro.org.entity.Mantenimiento;
import com.Metro.org.service.MantenimientoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mantenimientos")
@Validated
public class MantenimientoController {

    private final MantenimientoService mantenimientoService;

    public MantenimientoController(MantenimientoService mantenimientoService) {
        this.mantenimientoService = mantenimientoService;
    }

    @GetMapping
    public List<Mantenimiento> getAll() {
        return mantenimientoService.getAllMantenimientos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Integer id) {
        Mantenimiento mantenimiento = mantenimientoService.getMantenimientoById(id);
        if (mantenimiento == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No se encontró registro de mantenimiento con el ID " + id);
        }
        return ResponseEntity.ok(mantenimiento);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Mantenimiento mantenimiento) {
        if (mantenimiento.getDescripcion() == null || mantenimiento.getDescripcion().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Error: La descripción del mantenimiento es obligatoria y no puede estar vacía.");
        }

        if (mantenimiento.getIdTren() == null || mantenimiento.getIdTren() <= 0) {
            return ResponseEntity.badRequest().body("Error: El ID del tren debe ser un número positivo y existir en el sistema.");
        }

        Mantenimiento nuevo = mantenimientoService.saveMantenimiento(mantenimiento);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Mantenimiento mantenimiento) {
        Mantenimiento actualizado = mantenimientoService.updateMantenimiento(id, mantenimiento);
        if (actualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No se puede editar. El registro de mantenimiento con ID " + id + " no existe.");
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if (mantenimientoService.getMantenimientoById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No se puede eliminar. No existe mantenimiento con el ID " + id);
        }
        mantenimientoService.deleteMantenimiento(id);
        return ResponseEntity.ok("Mantenimiento eliminado exitosamente");
    }
}