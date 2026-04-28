package com.Metro.org.controller;

import com.Metro.org.entity.Conductores;
import com.Metro.org.service.ConductoresService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/conductores")
public class ConductorController {

    private final ConductoresService conductorService;

    public ConductorController(ConductoresService conductorService){
        this.conductorService = conductorService;
    }

    @GetMapping
    public String verPagina(@RequestParam(name = "buscarId", required = false) Integer buscarId,
                            Model model){

        List<Conductores> lista;

        if (buscarId != null) {
            Conductores conductor = conductorService.getConductoresById(buscarId);

            if (conductor != null) {
                lista = List.of(conductor);
            } else {
                lista = List.of();
            }
        } else {
            lista = conductorService.getAllConductores();
        }

        model.addAttribute("conductores", lista);
        model.addAttribute("conductor", new Conductores());

        return "Conductores";
    }

    @PostMapping("/guardar")
    public String guardarConductor(@Valid @ModelAttribute("conductor") Conductores conductor,
                                   org.springframework.validation.BindingResult result,
                                   Model model) {

        if (result.hasErrors()) {
            model.addAttribute("conductores", conductorService.getAllConductores());
            return "Conductores";
        }

        conductorService.saveConductores(conductor);
        return "redirect:/conductores";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id){
        conductorService.deleteConductores(id);
        return "redirect:/conductores";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model){

        Conductores conductor = conductorService.getConductoresById(id);

        model.addAttribute("conductor", conductor);

        List<Conductores> lista = conductorService.getAllConductores();
        model.addAttribute("conductores", lista);

        return "Conductores";
    }
}