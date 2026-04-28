package com.Metro.org.controller;

import com.Metro.org.entity.Mantenimiento;
import com.Metro.org.service.MantenimientoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/mantenimientos")
public class MantenimientoController {

    private final MantenimientoService mantenimientoService;

    public MantenimientoController(MantenimientoService mantenimientoService) {
        this.mantenimientoService = mantenimientoService;
    }

    @GetMapping
    public String listar(@RequestParam(name = "buscarTren", required = false) Integer buscarTren, Model model) {
        List<Mantenimiento> lista = (buscarTren != null) ? mantenimientoService.getMantenimientosByTren(buscarTren) : mantenimientoService.getAllMantenimientos();
        model.addAttribute("mantenimientos", lista);
        model.addAttribute("mantenimientoObj", new Mantenimiento());
        return "Mantenimiento";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Mantenimiento mantenimiento, RedirectAttributes redirectAttrs) {
        try {
            if (mantenimiento.getIdMantenimiento() != null && mantenimiento.getIdMantenimiento() > 0) {
                mantenimientoService.updateMantenimiento(mantenimiento.getIdMantenimiento(), mantenimiento);
                redirectAttrs.addFlashAttribute("mensajeExito", "Registro actualizado correctamente.");
            } else {
                mantenimientoService.saveMantenimiento(mantenimiento);
                redirectAttrs.addFlashAttribute("mensajeExito", "Mantenimiento guardado con éxito.");
            }
        } catch (RuntimeException e) {
            redirectAttrs.addFlashAttribute("mensajeError", e.getMessage());
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("mensajeError", "Error inesperado: " + e.getMessage());
        }
        return "redirect:/mantenimientos";
    }

    @GetMapping("/editar/{id}")
    public String precargarEdicion(@PathVariable Integer id, Model model) {
        Mantenimiento mantenimiento = mantenimientoService.getMantenimientoById(id);
        if (mantenimiento == null) {
            return "redirect:/mantenimientos";
        }
        model.addAttribute("mantenimientos", mantenimientoService.getAllMantenimientos());
        model.addAttribute("mantenimientoObj", mantenimiento);
        model.addAttribute("editando", true);
        return "Mantenimiento";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttrs) {
        try {
            mantenimientoService.deleteMantenimiento(id);
            redirectAttrs.addFlashAttribute("mensajeExito", "Mantenimiento eliminado.");
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("mensajeError", "No se pudo eliminar el registro.");
        }
        return "redirect:/mantenimientos";
    }

    @GetMapping("/api/listar")
    @ResponseBody
    public List<Mantenimiento> getAllApi() {
        return mantenimientoService.getAllMantenimientos();
    }
}