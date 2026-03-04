package com.Metro.org.controller;

import com.Metro.org.entity.Horario;
import com.Metro.org.service.HorarioService;
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
    public ResponseEntity<Object> getById(@PathVariable Integer id) {
        Horario horario = horarioService.getHorarioById(id);
        if (horario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: El horario con ID " + id + " no existe en la base de datos.");
        }
        return ResponseEntity.ok(horario);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Horario horario) {
        if (horario.getHoraSalida() == null || horario.getHoraLlegada() == null) {
            return ResponseEntity.badRequest().body("Error: Los campos de hora salida y llegada son obligatorios.");
        }

        if (horario.getIdTren() == null || horario.getIdTren() <= 0) {
            return ResponseEntity.badRequest().body("Error: El ID del tren proporcionado no es válido o no existe.");
        }

        Horario nuevo = horarioService.saveHorario(horario);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Horario horario) {
        if (horario.getIdTren() == null || horario.getIdTren() <= 0) {
            return ResponseEntity.badRequest().body("Error: Debe proporcionar un ID de tren válido para actualizar.");
        }

        Horario updated = horarioService.updateHorario(id, horario);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No se pudo actualizar. El horario con ID " + id + " no existe.");
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if (horarioService.getHorarioById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No se puede eliminar. El horario con ID " + id + " no existe.");
        }
        horarioService.deleteHorario(id);
        return ResponseEntity.ok("Horario eliminado correctamente");
    }
}