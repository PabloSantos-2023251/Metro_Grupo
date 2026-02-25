package com.Metro.org.Controller;

import com.Metro.org.Entity.Mantenimiento;
import com.Metro.org.Service.MantenimientoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mantenimientos")
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
        if (mantenimiento != null) {
            return ResponseEntity.ok(mantenimiento);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Mantenimiento> create(@RequestBody Mantenimiento mantenimiento) {
        Mantenimiento nuevo = mantenimientoService.saveMantenimiento(mantenimiento);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mantenimiento> update(@PathVariable Integer id, @RequestBody Mantenimiento mantenimiento) {
        Mantenimiento actualizado = mantenimientoService.updateMantenimiento(id, mantenimiento);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        mantenimientoService.deleteMantenimiento(id);
        return ResponseEntity.ok("Mantenimiento eliminado exitosamente");
    }
}