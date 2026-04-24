package com.Metro.org.controller;

import com.Metro.org.entity.Pasajero;
import com.Metro.org.service.PasajeroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pasajeros")
public class PasajeroController {

    private final PasajeroService pasajeroService;

    public PasajeroController(PasajeroService pasajeroService) {
        this.pasajeroService = pasajeroService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pasajeros", pasajeroService.getAllPasajeros());
        model.addAttribute("pasajeroForm", new Pasajero());
        return "Pasajeros";
    }

    @PostMapping("/agregar")
    public String agregar(@ModelAttribute("pasajeroForm") Pasajero pasajero) {
        pasajeroService.savePasajero(pasajero);
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
    public String editar(@PathVariable Integer id,
                         @ModelAttribute("pasajeroForm") Pasajero pasajero) {
        pasajeroService.updatePasajero(id, pasajero);
        return "redirect:/pasajeros";
    }

    @GetMapping("/borrar/{id}")
    public String borrar(@PathVariable Integer id) {
        pasajeroService.deletePasajero(id);
        return "redirect:/pasajeros";
    }
}