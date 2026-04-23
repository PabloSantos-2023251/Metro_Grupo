package com.Metro.org.controller;

import com.Metro.org.entity.Linea;
import com.Metro.org.service.LineaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/linea")
public class LineaController {

    private final LineaService lineaService;

    public LineaController(LineaService lineaService){
        this.lineaService = lineaService;
    }

    @GetMapping
    public String verPagina(Model model){

        List<Linea> lista = lineaService.getAllLinea();

        model.addAttribute("lineas", lista);
        model.addAttribute("linea", new Linea());

        return "Linea";
    }

    @PostMapping("/guardar")
    public String guardarLinea(@Valid @ModelAttribute("linea") Linea linea,
                               BindingResult result,
                               Model model){

        if(result.hasErrors()){
            model.addAttribute("lineas", lineaService.getAllLinea());
            return "Linea";
        }

        lineaService.saveLinea(linea);
        return "redirect:/linea";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id){
        lineaService.deleteLinea(id);
        return "redirect:/linea";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model){

        Linea linea = lineaService.getLineaById(id);

        model.addAttribute("linea", linea);

        List<Linea> lista = lineaService.getAllLinea();
        model.addAttribute("lineas", lista);

        return "linea";
    }
}