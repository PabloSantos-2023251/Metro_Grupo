package com.Metro.org.controller;

import com.Metro.org.entity.Trenes;
import com.Metro.org.service.TrenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public String verPagina(Model model){
        model.addAttribute("trenes", trenService.getAllTren());
        model.addAttribute("tren", new Trenes());
        return "Trenes";
    }

    @PostMapping("/guardar")
    public String guardarTren(@ModelAttribute("tren") Trenes tren){
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