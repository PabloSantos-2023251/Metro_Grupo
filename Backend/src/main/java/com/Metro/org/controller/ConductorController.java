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

    // 📌 Vista principal
    @GetMapping
    public String verPagina(Model model){

        List<Conductores> lista = conductorService.getAllConductores();

        model.addAttribute("conductores", lista);
        model.addAttribute("conductor", new Conductores());

        return "Conductores"; // nombre del HTML
    }

    // 📌 Guardar desde formulario
    @PostMapping("/guardar")
    public String guardarConductor(@Valid @ModelAttribute("conductor") Conductores conductor){
        conductorService.saveConductores(conductor);
        return "redirect:/conductores";
    }

    // 📌 Eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id){
        conductorService.deleteConductores(id);
        return "redirect:/conductores";
    }

    // 📌 Editar (vista)
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model){

        Conductores conductor = conductorService.getByIdconductroes(id);

        model.addAttribute("conductor", conductor);

        List<Conductores> lista = conductorService.getAllConductores();
        model.addAttribute("conductores", lista);

        return "Conductores";
    }
}