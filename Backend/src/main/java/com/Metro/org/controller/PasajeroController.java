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

    @GetMapping
    public String listar(@RequestParam(name = "buscarId", required = false) Integer buscarId, Model model) {
        List<Pasajero> lista;
        if (buscarId != null) {
            try {
                lista = List.of(pasajeroService.getPasajeroById(buscarId));
            } catch (RuntimeException e) {
                lista = List.of();
                model.addAttribute("mensajeVacio", "No se encontró ningún pasajero con ID: " + buscarId);
            }
        } else {
            lista = pasajeroService.getAllPasajeros();
        }

        model.addAttribute("pasajeros", lista);

        // Si no viene de un error de validación, creamos un objeto nuevo para el formulario
        if (!model.containsAttribute("pasajeroForm")) {
            model.addAttribute("pasajeroForm", new Pasajero());
        }
        return "Pasajeros";
    }

    @PostMapping("/agregar")
    public String agregar(@ModelAttribute("pasajeroForm") Pasajero pasajero, RedirectAttributes redirectAttributes) {
        if (pasajero.getRol() == null) {
            pasajero.setRol("pasajero");
        }
        Pasajero guardado = pasajeroService.savePasajero(pasajero);
        redirectAttributes.addFlashAttribute("nuevoId", guardado.getIdPasajero());
        return "redirect:/pasajeros";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {
        Pasajero pasajero = pasajeroService.getPasajeroById(id);
        model.addAttribute("pasajeros", pasajeroService.getAllPasajeros());
        model.addAttribute("pasajeroForm", pasajero);
        return "Pasajeros";
    }

    @PostMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, @ModelAttribute("pasajeroForm") Pasajero pasajero) {
        pasajeroService.updatePasajero(id, pasajero);
        return "redirect:/pasajeros";
    }

    @GetMapping("/borrar/{id}")
    public String borrar(@PathVariable Integer id) {
        pasajeroService.deletePasajero(id);
        return "redirect:/pasajeros";
    }
}