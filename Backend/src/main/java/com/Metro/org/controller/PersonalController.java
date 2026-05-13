package com.Metro.org.controller;

import com.Metro.org.entity.Personal;
import com.Metro.org.service.PersonalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/personal")
public class PersonalController {

    private final PersonalService personalService;
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";

    public PersonalController(PersonalService service) {
        this.personalService = service;
    }

    @GetMapping({"", "/"})
    public String listar(@RequestParam(required = false) String buscar, Model model) {
        try {
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
                model.addAttribute("editando", false);
            }
        } catch (Exception e) {
            model.addAttribute("mensajeError", "Error al conectar con la base de datos: " + e.getMessage());
        }
        return "Personal";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("personalObj") Personal personal, RedirectAttributes ra) {
        try {
            if (personal.getNombre() == null || personal.getNombre().trim().isEmpty() ||
                    personal.getEmail() == null || personal.getEmail().trim().isEmpty()) {
                ra.addFlashAttribute("mensajeError", "Nombre y Email son obligatorios.");
                ra.addFlashAttribute("personalObj", personal);
                return "redirect:/api/personal/";
            }

            if (!Pattern.matches(EMAIL_PATTERN, personal.getEmail())) {
                ra.addFlashAttribute("mensajeError", "Formato de email no válido.");
                ra.addFlashAttribute("personalObj", personal);
                return "redirect:/api/personal/";
            }

            if (personal.getId_personal() == null && (personal.getPassword() == null || personal.getPassword().length() < 8)) {
                ra.addFlashAttribute("mensajeError", "La contraseña debe tener al menos 8 caracteres.");
                ra.addFlashAttribute("personalObj", personal);
                return "redirect:/api/personal/";
            }

            List<Personal> todos = personalService.getAllPersonal();

            boolean emailRepetido = todos.stream()
                    .anyMatch(p -> p.getEmail().equalsIgnoreCase(personal.getEmail()) &&
                            !p.getId_personal().equals(personal.getId_personal()));

            if (emailRepetido) {
                ra.addFlashAttribute("mensajeError", "El correo ya está registrado.");
                ra.addFlashAttribute("personalObj", personal);
                return "redirect:/api/personal/";
            }

            personalService.savePersonal(personal);
            ra.addFlashAttribute("mensajeExito", "Operación exitosa.");

        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "Error de sistema: " + e.getMessage());
        }
        return "redirect:/api/personal/";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, RedirectAttributes ra) {
        try {
            Personal p = personalService.getPersonalById(id);
            if (p != null) {
                ra.addFlashAttribute("personalObj", p);
                ra.addFlashAttribute("editando", true);
            }
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo cargar el registro.");
        }
        return "redirect:/api/personal/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes ra) {
        try {
            personalService.deletePersonal(id);
            ra.addFlashAttribute("mensajeExito", "Registro eliminado correctamente.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se puede eliminar: el registro está siendo usado en otra tabla.");
        }
        return "redirect:/api/personal/";
    }
}