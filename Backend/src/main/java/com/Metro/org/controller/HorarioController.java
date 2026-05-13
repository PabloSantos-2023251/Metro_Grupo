package com.Metro.org.controller;

import com.Metro.org.entity.Horario;
import com.Metro.org.service.HorarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/horarios")
public class HorarioController {

    private final HorarioService horarioService;

    public HorarioController(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    @GetMapping({"", "/"})
    public String listar(@RequestParam(name = "buscarTren", required = false) Integer buscarTren, Model model) {
        List<Horario> lista = (buscarTren != null) ? horarioService.getHorariosByTren(buscarTren) : horarioService.getAllHorarios();
        model.addAttribute("horarios", lista);
        if (!model.containsAttribute("horarioObj")) {
            model.addAttribute("horarioObj", new Horario());
        }
        return "Horario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Horario horario, RedirectAttributes redirectAttrs) {
        try {
            if (horario.getIdHorario() != null && horario.getIdHorario() > 0) {
                horarioService.updateHorario(horario.getIdHorario(), horario);
                redirectAttrs.addFlashAttribute("mensajeExito", "Horario actualizado.");
            } else {
                horarioService.saveHorario(horario);
                redirectAttrs.addFlashAttribute("mensajeExito", "Horario programado.");
            }
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("mensajeError", "Error: " + e.getMessage());
        }
        return "redirect:/horarios/";
    }

    @GetMapping("/editar/{id}")
    public String precargarEdicion(@PathVariable Integer id, RedirectAttributes ra) {
        Horario horario = horarioService.getHorarioById(id);
        if (horario != null) {
            ra.addFlashAttribute("horarioObj", horario);
            ra.addFlashAttribute("editando", true);
        }
        return "redirect:/horarios/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttrs) {
        horarioService.deleteHorario(id);
        redirectAttrs.addFlashAttribute("mensajeExito", "Horario eliminado.");
        return "redirect:/horarios/";
    }
}