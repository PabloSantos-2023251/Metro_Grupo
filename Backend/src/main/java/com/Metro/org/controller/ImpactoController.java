package com.Metro.org.controller;

import com.PabloSantos.org.entity.ImpactoTrafico;
import com.PabloSantos.org.service.ImpactoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/impacto-trafico")
public class ImpactoController {
    private final ImpactoService impactoService;

    public ImpactoController(ImpactoService impactoService) {
        this.impactoService = impactoService;
    }

    @GetMapping
    public List<ImpactoTrafico> getAllImpactos() {
        return impactoService.getAllImpactos();
    }

    @PostMapping
    public ResponseEntity<Object> createImpacto(@Valid @RequestBody ImpactoTrafico impacto) {
        try {
            double porcentaje = impacto.getReduccionTraficoPorcentaje().doubleValue();
            if (porcentaje < 0 || porcentaje > 100) {
                throw new IllegalArgumentException("El porcentaje debe estar entre 0 y 100.");
            }

            ImpactoTrafico createdImpacto = impactoService.saveImpacto(impacto);
            return new ResponseEntity<>(createdImpacto, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateImpacto(@Valid @RequestBody ImpactoTrafico impacto) {
        try {
            Integer id = impacto.getId_impacto();
            if (id == null) {
                return ResponseEntity.badRequest().body("Error: El ID del impacto es obligatorio en el JSON.");
            }

            double porcentaje = impacto.getReduccionTraficoPorcentaje().doubleValue();
            if (porcentaje < 0 || porcentaje > 100) {
                throw new IllegalArgumentException("El porcentaje debe estar entre 0 y 100.");
            }

            ImpactoTrafico updatedImpacto = impactoService.updateImpacto(id, impacto);
            return new ResponseEntity<>(updatedImpacto, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteImpacto(@Valid @RequestBody ImpactoTrafico impacto) {
        try {
            Integer id = impacto.getId_impacto();
            if (id == null) {
                return ResponseEntity.badRequest().body("Error: El ID del impacto es obligatorio en el JSON.");
            }
            impactoService.deleteImpacto(id);
            return ResponseEntity.ok().body("Impacto eliminado correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}