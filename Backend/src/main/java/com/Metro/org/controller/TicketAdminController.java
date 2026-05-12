package com.Metro.org.controller;

import com.Metro.org.entity.TicketSoporte;
import com.Metro.org.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*; // Para GetMapping, PostMapping, etc.
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ComentarioAdmin")
public class TicketAdminController {

    private final TicketService ticketService;

    public TicketAdminController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/admin")
    public String adminList(Model model) {
        model.addAttribute("comentarios", ticketService.getAll());
        return "comentarioAdmin";
    }


    @GetMapping("/detalle/{id}")
    public String verDetalle(@PathVariable Integer id, Model model) {
        TicketSoporte ticket = ticketService.buscarPorId(id).orElse(null);
        if (ticket != null) {
            model.addAttribute("ticket", ticket);
            return "comentarioDetalle";
        }
        return "redirect:/ComentarioAdmin/admin";
    }

    @PostMapping("/actualizar-estado")
    public String actualizarEstado(@RequestParam Integer id,
                                   @RequestParam String nuevoEstado,
                                   @RequestParam(value = "origen", defaultValue = "admin") String origen,
                                   RedirectAttributes ra) {
        try {
            TicketSoporte.EstadoTicket estado = TicketSoporte.EstadoTicket.valueOf(nuevoEstado.toLowerCase());
            ticketService.cambiarEstado(id, estado);
            ra.addFlashAttribute("mensajeExito", "Estado actualizado correctamente.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "Error al actualizar el estado: " + e.getMessage());
        }

        if ("detalle".equals(origen)) {
            return "redirect:/ComentarioAdmin/detalle/" + id;
        }
        return "redirect:/ComentarioAdmin/admin";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarTicket(@PathVariable Integer id, RedirectAttributes ra) {
        try {
            ticketService.delete(id);
            ra.addFlashAttribute("mensajeExito", "Ticket eliminado exitosamente.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el ticket.");
        }
        return "redirect:/ComentarioAdmin/admin";
    }
}