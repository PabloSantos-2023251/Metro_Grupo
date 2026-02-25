package com.Metro.org.Controller;

import com.Metro.org.Entity.Horario;
import com.Metro.org.Service.HorarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horarios")
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
        return (horario != null) ? ResponseEntity.ok(horario) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Horario> create(@RequestBody Horario horario) {
        return new ResponseEntity<>(horarioService.saveHorario(horario), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Horario> update(@PathVariable Integer id, @RequestBody Horario horario) {
        Horario updated = horarioService.updateHorario(id, horario);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            horarioService.deleteHorario(id);
            return ResponseEntity.ok("Horario eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar");
        }
    }
}