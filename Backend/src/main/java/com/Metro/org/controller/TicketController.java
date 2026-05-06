package com.Metro.org.controller;

import com.Metro.org.entity.TicketSoporte;
import com.Metro.org.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/soporte")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("ticketObj", new TicketSoporte());
        model.addAttribute("tickets", ticketService.getAll());
        return "Soporte";
    }

    @PostMapping("/enviar")
    public String enviar(@ModelAttribute TicketSoporte ticket, RedirectAttributes ra) {
        try {
            ticketService.save(ticket);
            ra.addFlashAttribute("mensajeExito", "Solicitud enviada correctamente.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "Error al procesar la solicitud.");
        }
        return "redirect:/soporte";
    }
}