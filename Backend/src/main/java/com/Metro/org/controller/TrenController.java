package com.Metro.org.controller;

import com.Metro.org.entity.Trenes;
import com.Metro.org.service.TrenService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/trenes")
public class TrenController {

    private final TrenService trenService;

    public TrenController(TrenService trenService){
        this.trenService = trenService;
    }

    @GetMapping({"", "/"})
    public String verPagina(@RequestParam(name = "buscarId", required = false) Integer buscarId, Model model){
        List<Trenes> lista;
        if (buscarId != null) {
            Trenes tren = trenService.getTrenById(buscarId);
            lista = (tren != null) ? List.of(tren) : List.of();
        } else {
            lista = trenService.getAllTren();
        }

        model.addAttribute("trenes", lista);
        if (!model.containsAttribute("tren")) {
            model.addAttribute("tren", new Trenes());
        }
        return "Trenes";
    }

    @PostMapping("/guardar")
    public String guardarTren(@ModelAttribute("tren") Trenes tren, RedirectAttributes ra){
        trenService.saveTren(tren);
        ra.addFlashAttribute("mensajeExito", "Tren guardado correctamente.");
        return "redirect:/trenes/";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, RedirectAttributes ra){
        Trenes tren = trenService.getTrenById(id);
        ra.addFlashAttribute("tren", tren);
        return "redirect:/trenes/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes ra){
        trenService.deleteTren(id);
        ra.addFlashAttribute("mensajeExito", "Tren eliminado.");
        return "redirect:/trenes/";
    }
}