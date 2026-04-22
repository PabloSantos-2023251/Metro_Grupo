package com.Metro.org.controller;

import com.Metro.org.entity.ImpactoTrafico;
import com.Metro.org.service.ImpactoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ImpactoController {

    private final ImpactoService impactoService;

    public ImpactoController(ImpactoService service) {
        this.impactoService = service;
    }

    @GetMapping("/api/impacto-trafico")
    public String listar(@RequestParam(required = false) String buscar, Model model) {
        List<ImpactoTrafico> impactos = impactoService.getAllImpactos();

        if (buscar != null && !buscar.isEmpty()) {
            impactos = impactos.stream()
                    .filter(i -> (i.getZona() != null && i.getZona().toLowerCase().contains(buscar.toLowerCase())) ||
                            (i.getId_impacto() != null && i.getId_impacto().toString().contains(buscar)))
                    .collect(Collectors.toList());
        }

        model.addAttribute("impactos", impactos);
        model.addAttribute("impactoObj", new ImpactoTrafico());
        return "Impacto";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute ImpactoTrafico impacto, RedirectAttributes redirectAttrs) {
        if (impacto.getReduccionTraficoPorcentaje() != null) {
            double porcentaje = impacto.getReduccionTraficoPorcentaje().doubleValue();
            if (porcentaje < 0 || porcentaje > 100) {
                redirectAttrs.addFlashAttribute("mensajeError", "El porcentaje debe estar entre 0 y 100.");
                return "redirect:/api/impacto-trafico";
            }
        }

        impactoService.saveImpacto(impacto);
        return "redirect:/api/impacto-trafico";
    }

    @GetMapping("/editar/{id}")
    public String precargarEdicion(@PathVariable Integer id, Model model) {
        ImpactoTrafico impacto = impactoService.getImpactoById(id);
        if (impacto == null) {
            return "redirect:/api/impacto-trafico";
        }
        model.addAttribute("impactos", impactoService.getAllImpactos());
        model.addAttribute("impactoObj", impacto);
        model.addAttribute("editando", true);
        return "Impacto";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttrs) {
        try {
            impactoService.deleteImpacto(id);
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("mensajeError", "No se puede eliminar el registro.");
        }
        return "redirect:/api/impacto-trafico";
    }
}