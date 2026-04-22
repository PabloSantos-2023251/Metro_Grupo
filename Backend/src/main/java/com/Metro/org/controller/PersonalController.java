package com.Metro.org.controller;

import com.Metro.org.entity.Personal;
import com.Metro.org.service.PersonalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PersonalController {

    private final PersonalService personalService;

    public PersonalController(PersonalService service) {
        this.personalService = service;
    }

    @GetMapping("/api/personal")
    public String listar(@RequestParam(required = false) String buscar, Model model) {
        List<Personal> personalList = personalService.getAllPersonal();

        if (buscar != null && !buscar.isEmpty()) {
            String criterio = buscar.toLowerCase();
            personalList = personalList.stream()
                    .filter(p -> p.getNombre().toLowerCase().contains(criterio) ||
                            p.getEmail().toLowerCase().contains(criterio) ||
                            p.getCargo().toLowerCase().contains(criterio) ||
                            p.getId_personal().toString().contains(buscar))
                    .collect(Collectors.toList());
        }

        model.addAttribute("personalList", personalList);
        model.addAttribute("personalObj", new Personal());
        return "Personal";
    }

    @PostMapping("/api/personal/guardar")
    public String guardar(@ModelAttribute Personal personal, RedirectAttributes redirectAttrs) {
        try {
            personalService.savePersonal(personal);
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("mensajeError", "Error al guardar: El email ya existe o los datos son inválidos.");
        }
        return "redirect:/api/personal";
    }

    @GetMapping("/api/personal/editar/{id}")
    public String precargarEdicion(@PathVariable Integer id, Model model) {
        model.addAttribute("personalList", personalService.getAllPersonal());
        model.addAttribute("personalObj", personalService.getPersonalById(id));
        model.addAttribute("editando", true);
        return "Personal";
    }

    @GetMapping("/api/personal/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttrs) {
        try {
            personalService.deletePersonal(id);
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("mensajeError", "No se puede eliminar el registro de personal.");
        }
        return "redirect:/api/personal";
    }
}