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

    // --- RUTA PRINCIPAL PARA VER LA VISTA ---
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("horarios", horarioService.getAllHorarios());
        model.addAttribute("horarioObj", new Horario());
        return "Horario"; // Retorna Horario.html
    }

    // --- ACCIÓN DE GUARDAR Y ACTUALIZAR ---
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Horario horario, RedirectAttributes redirectAttrs) {
        try {
            if (horario.getIdHorario() != null && horario.getIdHorario() > 0) {
                horarioService.updateHorario(horario.getIdHorario(), horario);
            } else {
                horarioService.saveHorario(horario);
            }
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("mensajeError", "Error al procesar el horario: " + e.getMessage());
        }
        return "redirect:/horarios";
    }

    // --- PRECARGAR DATOS PARA EDICIÓN ---
    @GetMapping("/editar/{id}")
    public String precargarEdicion(@PathVariable Integer id, Model model) {
        Horario horario = horarioService.getHorarioById(id);
        if (horario == null) return "redirect:/horarios";

        model.addAttribute("horarios", horarioService.getAllHorarios());
        model.addAttribute("horarioObj", horario);
        model.addAttribute("editando", true);
        return "Horario";
    }

    // --- ACCIÓN DE ELIMINAR ---
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        horarioService.deleteHorario(id);
        return "redirect:/horarios";
    }

    // --- API OPCIONAL (JSON) ---
    @GetMapping("/api/listar")
    @ResponseBody
    public List<Horario> getAllApi() {
        return horarioService.getAllHorarios();
    }
}