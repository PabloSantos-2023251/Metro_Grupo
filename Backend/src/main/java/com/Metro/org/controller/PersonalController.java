package com.Metro.org.controller;

import com.PabloSantos.org.entity.Personal;
import com.PabloSantos.org.service.PersonalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personal")
public class PersonalController {
    private final PersonalService personalService;

    public PersonalController(PersonalService personalService) {
        this.personalService = personalService;
    }

    @GetMapping
    public List<Personal> getAllPersonal() {
        return personalService.getAllPersonal();
    }

    @PostMapping
    public ResponseEntity<Object> createPersonal(@Valid @RequestBody Personal personal) {
        try {
            Personal createdPersonal = personalService.savePersonal(personal);
            Integer id = personal.getId_personal();
            if (id == null) {
                return ResponseEntity.badRequest().body("Error: El ID del personal es obligatorio en el JSON.");
            } else {
                createdPersonal = personalService.updatePersonal(id, personal);
                return new ResponseEntity<>(createdPersonal, HttpStatus.OK);
            }

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<Object> updatePersonal(@Valid @RequestBody Personal personal) {
        try {
            Integer id = personal.getId_personal();
            Personal updatedPersonal;

            if (id == null) {
                return ResponseEntity.badRequest().body("Error: El ID del personal es obligatorio en el JSON.");
            } else {
                updatedPersonal = personalService.updatePersonal(id, personal);
                return new ResponseEntity<>(updatedPersonal, HttpStatus.OK);
            }

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> deletePersonal(@Valid @RequestBody Personal personal) {
        try {
            Integer id = personal.getId_personal();

            if (id == null) {
                return ResponseEntity.badRequest().body("Error: El ID del personal es obligatorio en el JSON.");
            }
            personalService.deletePersonal(id);

            return ResponseEntity.ok().body("Personal eliminado correctamente");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}