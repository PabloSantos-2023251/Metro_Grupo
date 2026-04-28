package com.Metro.org.controller;

import com.Metro.org.entity.Trenes;
import com.Metro.org.service.TrenService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/trenes")
public class TrenController {

    private final TrenService trenService;

    public TrenController(TrenService trenService){
        this.trenService = trenService;
    }

    @GetMapping
    public String verPagina(@RequestParam(name = "buscarId", required = false) Integer buscarId,
                            Model model){

        List<Trenes> lista;

        if (buscarId != null) {
            Trenes tren = trenService.getTrenById(buscarId);

            if (tren != null) {
                lista = List.of(tren);
            } else {
                lista = List.of();
            }
        } else {
            lista = trenService.getAllTren();
        }

        model.addAttribute("trenes", lista);
        model.addAttribute("tren", new Trenes());

        return "Trenes";
    }

    @PostMapping("/guardar")
    public String guardarTren(@Valid @ModelAttribute("tren") Trenes tren,
                              org.springframework.validation.BindingResult result,
                              Model model) {

        if (result.hasErrors()) {
            model.addAttribute("trenes", trenService.getAllTren());
            return "Trenes";
        }

        trenService.saveTren(tren);
        return "redirect:/trenes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model){
        model.addAttribute("tren", trenService.getTrenById(id));
        model.addAttribute("trenes", trenService.getAllTren());
        return "Trenes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id){
        trenService.deleteTren(id);
        return "redirect:/trenes";
    }
}