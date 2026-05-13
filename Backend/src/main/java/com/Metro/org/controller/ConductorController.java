package com.Metro.org.controller;

import com.Metro.org.entity.Conductores;
import com.Metro.org.service.ConductoresService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/conductores")
public class ConductorController {

    private final ConductoresService conductorService;

    public ConductorController(ConductoresService conductorService){
        this.conductorService = conductorService;
    }

    @GetMapping({"", "/"})
    public String verPagina(@RequestParam(name = "buscarId", required = false) Integer buscarId, Model model){
        List<Conductores> lista = (buscarId != null) ?
                List.of(conductorService.getConductoresById(buscarId)) : conductorService.getAllConductores();

        model.addAttribute("conductores", lista);
        if (!model.containsAttribute("conductor")) {
            model.addAttribute("conductor", new Conductores());
        }
        return "Conductores";
    }

    @PostMapping("/guardar")
    public String guardarConductor(@Valid @ModelAttribute("conductor") Conductores conductor, RedirectAttributes ra){
        conductorService.saveConductores(conductor);
        ra.addFlashAttribute("mensajeExito", "Conductor actualizado/guardado.");
        return "redirect:/conductores/";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, RedirectAttributes ra){
        Conductores conductor = conductorService.getConductoresById(id);
        ra.addFlashAttribute("conductor", conductor);
        return "redirect:/conductores/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes ra){
        conductorService.deleteConductores(id);
        ra.addFlashAttribute("mensajeExito", "Conductor eliminado.");
        return "redirect:/conductores/";
    }
}