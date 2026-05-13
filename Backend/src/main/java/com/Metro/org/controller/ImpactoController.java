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

    @GetMapping({"", "/"})
    public String listar(@RequestParam(required = false) String buscar, Model model) {
        List<ImpactoTrafico> impactos = impactoService.getAllImpactos();
        if (buscar != null && !buscar.isEmpty()) {
            String criterio = buscar.toLowerCase();
            impactos = impactos.stream()
                    .filter(i -> (i.getZona() != null && i.getZona().toLowerCase().contains(criterio)))
                    .collect(Collectors.toList());
        }
        model.addAttribute("impactos", impactos);
        if (!model.containsAttribute("impactoObj")) {
            model.addAttribute("impactoObj", new ImpactoTrafico());
        }
        return "Impacto";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute ImpactoTrafico impacto, RedirectAttributes ra) {
        try {
            impactoService.saveImpacto(impacto);
            ra.addFlashAttribute("mensajeExito", "Análisis guardado correctamente.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "Error al procesar el registro.");
        }
        return "redirect:/api/impacto-trafico/";
    }

    @GetMapping("/editar/{id}")
    public String precargarEdicion(@PathVariable Integer id, RedirectAttributes ra) {
        ImpactoTrafico impacto = impactoService.getImpactoById(id);
        ra.addFlashAttribute("impactoObj", impacto);
        ra.addFlashAttribute("editando", true);
        return "redirect:/api/impacto-trafico/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes ra) {
        impactoService.deleteImpacto(id);
        ra.addFlashAttribute("mensajeExito", "Registro eliminado.");
        return "redirect:/api/impacto-trafico/";
    }
}