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
@RequestMapping("/api/personal")
public class PersonalController {

    private final PersonalService personalService;

    public PersonalController(PersonalService service) {
        this.personalService = service;
    }

    @GetMapping
    public String listar(@RequestParam(required = false) String buscar, Model model) {
        List<Personal> lista = personalService.getAllPersonal();
        if (buscar != null && !buscar.isEmpty()) {
            String b = buscar.toLowerCase();
            lista = lista.stream()
                    .filter(p -> p.getNombre().toLowerCase().contains(b) || p.getEmail().toLowerCase().contains(b))
                    .collect(Collectors.toList());
        }
        model.addAttribute("personalList", lista);
        if (!model.containsAttribute("personalObj")) {
            model.addAttribute("personalObj", new Personal());
        }
        return "Personal";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Personal personal, RedirectAttributes ra) {
        try {
            personalService.savePersonal(personal);
            ra.addFlashAttribute("mensajeExito", "Guardado correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "Error al guardar" + e);
        }
        return "redirect:/api/personal";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, RedirectAttributes ra) {
        Personal p = personalService.getPersonalById(id);
        ra.addFlashAttribute("personalObj", p); // Esto lo pasa al GET de listar
        ra.addFlashAttribute("editando", true);
        return "redirect:/api/personal";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        personalService.deletePersonal(id);
        return "redirect:/api/personal";
    }
}