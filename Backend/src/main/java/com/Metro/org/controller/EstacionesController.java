package com.Metro.org.controller;

import com.Metro.org.entity.Estaciones;
import com.Metro.org.service.EstacionesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/estaciones")
public class EstacionesController {

    private final EstacionesService estacionesService;

    public EstacionesController(EstacionesService estacionesService){
        this.estacionesService = estacionesService;
    }

    @GetMapping({"", "/"})
    public String listar(@RequestParam(required = false) String buscar, Model model) {
        List<Estaciones> estaciones = estacionesService.getAllEstaciones();
        if (buscar != null && !buscar.isEmpty()) {
            estaciones = estaciones.stream()
                    .filter(e -> e.getIdEstacion() != null && e.getIdEstacion().toString().contains(buscar))
                    .collect(Collectors.toList());
        }

        model.addAttribute("estaciones", estaciones);
        if (!model.containsAttribute("estacion")) {
            model.addAttribute("estacion", new Estaciones());
        }
        return "estacion";
    }

    @PostMapping("/guardar")
    public String guardarEstacion(@ModelAttribute Estaciones estacion, RedirectAttributes ra){
        estacionesService.saveEstaciones(estacion);
        ra.addFlashAttribute("mensajeExito", "Estación actualizada.");
        return "redirect:/estaciones/";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, RedirectAttributes ra){
        Estaciones estacion = estacionesService.getEstacionesById(id);
        ra.addFlashAttribute("estacion", estacion);
        ra.addFlashAttribute("editando", true);
        return "redirect:/estaciones/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes ra){
        estacionesService.deleteEstaciones(id);
        ra.addFlashAttribute("mensajeExito", "Estación eliminada.");
        return "redirect:/estaciones/";
    }
}