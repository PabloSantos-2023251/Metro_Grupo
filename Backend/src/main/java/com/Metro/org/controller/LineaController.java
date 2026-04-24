package com.Metro.org.controller;

import com.Metro.org.entity.Linea;
import com.Metro.org.service.LineaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/linea")
public class LineaController {

    private final LineaService lineaService;

    public LineaController(LineaService lineaService){
        this.lineaService = lineaService;
    }

    @GetMapping
    public String listar(@RequestParam(required = false) String buscar, Model model){

        List<Linea> lineas = lineaService.getAllLinea();
        if (buscar != null && !buscar.isEmpty()) {
            lineas = lineas.stream()
                    .filter(l -> l.getIdLinea() != null &&
                            l.getIdLinea().toString().contains(buscar))
                    .collect(Collectors.toList());
        }

        model.addAttribute("lineas", lineas);
        model.addAttribute("linea", new Linea());

        return "linea";
    }

    @PostMapping("/guardar")
    public String guardarLinea(@Valid @ModelAttribute("linea") Linea linea,
                               BindingResult result,
                               Model model){

        if(result.hasErrors()){
            model.addAttribute("lineas", lineaService.getAllLinea());
            return "linea";
        }

        lineaService.saveLinea(linea);
        return "redirect:/linea";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model){

        Linea linea = lineaService.getLineaById(id);

        model.addAttribute("lineas", lineaService.getAllLinea());
        model.addAttribute("linea", linea);

        return "linea";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id){
        lineaService.deleteLinea(id);
        return "redirect:/linea";
    }
}