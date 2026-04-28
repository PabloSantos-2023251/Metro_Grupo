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
@RequestMapping("/api/impacto-trafico")
public class ImpactoController {

    private final ImpactoService impactoService;

    public ImpactoController(ImpactoService service) {
        this.impactoService = service;
    }

    @GetMapping
    public String listar(@RequestParam(required = false) String buscar, Model model) {
        List<ImpactoTrafico> impactos = impactoService.getAllImpactos();

        if (buscar != null && !buscar.isEmpty()) {
            String criterio = buscar.toLowerCase();
            impactos = impactos.stream()
                    .filter(i -> (i.getZona() != null && i.getZona().toLowerCase().contains(criterio)) ||
                            (i.getId_impacto() != null && i.getId_impacto().toString().contains(buscar)))
                    .collect(Collectors.toList());
        }

        model.addAttribute("impactos", impactos);
        if (!model.containsAttribute("impactoObj")) {
            model.addAttribute("impactoObj", new ImpactoTrafico());
            model.addAttribute("editando", false);
        }
        return "Impacto";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute ImpactoTrafico impacto, RedirectAttributes ra) {
        try {
            if (impacto.getZona() == null || impacto.getZona().trim().isEmpty()) {
                ra.addFlashAttribute("mensajeError", "La zona es obligatoria.");
                ra.addFlashAttribute("impactoObj", impacto);
                return "redirect:/api/impacto-trafico";
            }

            if (impacto.getReduccionTraficoPorcentaje() != null) {
                double val = impacto.getReduccionTraficoPorcentaje().doubleValue();
                if (val < 0 || val > 100) {
                    ra.addFlashAttribute("mensajeError", "El porcentaje debe estar entre 0 y 100.");
                    ra.addFlashAttribute("impactoObj", impacto);
                    return "redirect:/api/impacto-trafico";
                }
            }

            boolean zonaExiste = impactoService.getAllImpactos().stream()
                    .anyMatch(z -> z.getZona().equalsIgnoreCase(impacto.getZona()) &&
                            !z.getId_impacto().equals(impacto.getId_impacto()));

            if (zonaExiste) {
                ra.addFlashAttribute("mensajeError", "La zona ya está registrada.");
                ra.addFlashAttribute("impactoObj", impacto);
                return "redirect:/api/impacto-trafico";
            }

            impactoService.saveImpacto(impacto);
            ra.addFlashAttribute("mensajeExito", "Guardado correctamente.");

        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "Error al guardar.");
        }
        return "redirect:/api/impacto-trafico";
    }

    @GetMapping("/editar/{id}")
    public String precargarEdicion(@PathVariable Integer id, RedirectAttributes ra) {
        ImpactoTrafico impacto = impactoService.getImpactoById(id);
        if (impacto != null) {
            ra.addFlashAttribute("impactoObj", impacto);
            ra.addFlashAttribute("editando", true);
        }
        return "redirect:/api/impacto-trafico";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes ra) {
        try {
            impactoService.deleteImpacto(id);
            ra.addFlashAttribute("mensajeExito", "Eliminado correctamente.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "Error al eliminar.");
        }
        return "redirect:/api/impacto-trafico";
    }
}