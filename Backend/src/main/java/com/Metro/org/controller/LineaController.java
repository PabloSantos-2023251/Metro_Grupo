package com.Metro.org.controller;

import com.Metro.org.entity.Linea;
import com.Metro.org.service.LineaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/linea")
public class LineaController {

    private final LineaService lineaService;

    public LineaController(LineaService lineaService){
        this.lineaService = lineaService;
    }

    @GetMapping({"", "/"})
    public String listar(@RequestParam(required = false) String buscar, Model model){
        List<Linea> lineas = lineaService.getAllLinea();
        if (buscar != null && !buscar.isEmpty()) {
            lineas = lineas.stream()
                    .filter(l -> l.getIdLinea() != null && l.getIdLinea().toString().contains(buscar))
                    .collect(Collectors.toList());
        }

        model.addAttribute("lineas", lineas);

        if (!model.containsAttribute("linea")) {
            model.addAttribute("linea", new Linea());
        }
        return "linea";
    }

    @PostMapping("/guardar")
    public String guardarLinea(@Valid @ModelAttribute("linea") Linea linea, BindingResult result, RedirectAttributes ra){
        // 1. Validaciones de anotaciones de la Entidad (@NotBlank, @Size, etc.)
        if(result.hasErrors()){
            ra.addFlashAttribute("org.springframework.validation.BindingResult.linea", result);
            ra.addFlashAttribute("linea", linea);
            ra.addFlashAttribute("mensajeError", "Por favor, corrija los errores en el formulario.");
            return "redirect:/linea";
        }

        try {
            lineaService.saveLinea(linea);
            ra.addFlashAttribute("mensajeExito", "Línea guardada correctamente.");
        } catch (Exception e){
            // Si la base de datos falla, devolvemos el objeto y el error sin tumbar la app
            ra.addFlashAttribute("linea", linea);
            ra.addFlashAttribute("mensajeError", "Error al guardar en la base de datos: " + e.getMessage());
        }

        return "redirect:/linea";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, RedirectAttributes ra){
        try {
            Linea linea = lineaService.getLineaById(id);
            ra.addFlashAttribute("linea", linea);
            ra.addFlashAttribute("editando", true);
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo cargar la línea para editar.");
        }
        return "redirect:/linea";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes ra){
        try {
            lineaService.deleteLinea(id);
            ra.addFlashAttribute("mensajeExito", "Línea eliminada correctamente.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se puede eliminar esta línea porque está asociada a otros registros.");
        }
        return "redirect:/linea";
    }
}