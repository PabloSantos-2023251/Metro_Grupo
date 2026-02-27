package com.Metro.org.Controller;

import com.Metro.org.Entity.Horario;
import com.Metro.org.Service.HorarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horarios")
@Validated
public class HorarioController {

    private final HorarioService horarioService;

    public HorarioController(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    @GetMapping
    public List<Horario> getAll() {
        return horarioService.getAllHorarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Horario> getById(@PathVariable Integer id) {
        Horario horario = horarioService.getHorarioById(id);
        if (horario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(horario);
    }

    @PostMapping
    public ResponseEntity<Horario> create(@Valid @RequestBody Horario horario) {
        // @Valid atrapará si faltan campos o si el formato es incorrecto
        Horario nuevo = horarioService.saveHorario(horario);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Horario> update(@PathVariable Integer id, @Valid @RequestBody Horario horario) {
        Horario updated = horarioService.updateHorario(id, horario);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // El ID no existía
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            // Verificamos primero si existe para dar una respuesta coherente
            if (horarioService.getHorarioById(id) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se puede eliminar: El horario con ID " + id + " no existe.");
            }
            horarioService.deleteHorario(id);
            return ResponseEntity.ok("Horario eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error técnico al intentar eliminar el registro.");
        }
    }
}