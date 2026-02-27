package com.Metro.org.Controller;

import com.Metro.org.Entity.Mantenimiento;
import com.Metro.org.Service.MantenimientoService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Mantenimiento> getById(@PathVariable Integer id) {
        Mantenimiento mantenimiento = mantenimientoService.getMantenimientoById(id);
        return mantenimiento != null
                ? ResponseEntity.ok(mantenimiento)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Mantenimiento> create(@Valid @RequestBody Mantenimiento mantenimiento) {
        Mantenimiento nuevo = mantenimientoService.saveMantenimiento(mantenimiento);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mantenimiento> update(@PathVariable Integer id, @Valid @RequestBody Mantenimiento mantenimiento) {
        Mantenimiento actualizado = mantenimientoService.updateMantenimiento(id, mantenimiento);
        if (actualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if (mantenimientoService.getMantenimientoById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No existe mantenimiento con ID " + id);
        }
        mantenimientoService.deleteMantenimiento(id);
        return ResponseEntity.ok("Mantenimiento eliminado exitosamente");
    }
}