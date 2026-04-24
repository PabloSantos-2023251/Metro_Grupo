package com.Metro.org.controller;

import com.Metro.org.entity.Estaciones;
import com.Metro.org.service.EstacionesService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/estaciones")
public class EstacionesController {

    private final EstacionesService estacionesService;

    public EstacionesController(EstacionesService estacionesService){
        this.estacionesService = estacionesService;
    }

    @GetMapping
    public String verPagina(Model model){

        List<Estaciones> lista = estacionesService.getAllEstaciones();

        model.addAttribute("estaciones", lista);
        model.addAttribute("estacion", new Estaciones());

        return "estacion";
    }

    @PostMapping("/guardar")
    public String guardarEstacion(@Valid @ModelAttribute("estacion") Estaciones estacion){
        estacionesService.saveEstaciones(estacion);
        return "redirect:/estaciones";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id){
        estacionesService.deleteEstaciones(id);
        return "redirect:/estaciones";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model){

        Estaciones estacion = estacionesService.getEstacionesById(id);

        model.addAttribute("estacion", estacion);

        List<Estaciones> lista = estacionesService.getAllEstaciones();
        model.addAttribute("estaciones", lista);

        return "estacion";
    }
}