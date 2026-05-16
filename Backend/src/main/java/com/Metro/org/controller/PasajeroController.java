package com.Metro.org.controller;

import com.Metro.org.entity.Pasajero;
import com.Metro.org.service.PasajeroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/pasajeros")
public class PasajeroController {

    private final PasajeroService pasajeroService;

    public PasajeroController(PasajeroService pasajeroService) {
        this.pasajeroService = pasajeroService;
    }

    @GetMapping({"", "/"})
    public String listar(@RequestParam(name = "buscarId", required = false) Integer buscarId, Model model) {
        List<Pasajero> lista;
        if (buscarId != null) {
            try {
                lista = List.of(pasajeroService.getPasajeroById(buscarId));
            } catch (Exception e) {
                lista = List.of();
                model.addAttribute("mensajeError", "No se encontró el ID: " + buscarId);
            }
        } else {
            lista = pasajeroService.getAllPasajeros();
        }

        model.addAttribute("pasajeros", lista);
        if (!model.containsAttribute("pasajeroForm")) {
            model.addAttribute("pasajeroForm", new Pasajero());
        }
        return "Pasajeros";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("pasajeroForm") Pasajero pasajero, RedirectAttributes ra) {
        if (pasajero.getRol() == null) pasajero.setRol("pasajero");

        try {
            if (pasajero.getIdPasajero() != null) {
                pasajeroService.updatePasajero(pasajero.getIdPasajero(), pasajero);
                ra.addFlashAttribute("mensajeExito", "Pasajero actualizado correctamente.");
            } else {
                pasajeroService.savePasajero(pasajero);
                ra.addFlashAttribute("mensajeExito", "Pasajero registrado exitosamente.");
            }
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "Error al procesar el pasajero: " + e.getMessage());
        }
        return "redirect:/pasajeros";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, RedirectAttributes ra) {
        Pasajero pasajero = pasajeroService.getPasajeroById(id);
        ra.addFlashAttribute("pasajeroForm", pasajero);
        return "redirect:/pasajeros";
    }

    @GetMapping("/borrar/{id}")
    public String borrar(@PathVariable Integer id, RedirectAttributes ra) {
        try {
            pasajeroService.deletePasajero(id);
            ra.addFlashAttribute("mensajeExito", "Pasajero eliminado.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el pasajero.");
        }
        return "redirect:/pasajeros";
    }
}