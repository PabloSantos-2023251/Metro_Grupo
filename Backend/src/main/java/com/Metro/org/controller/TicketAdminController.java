package com.Metro.org.controller;

import com.Metro.org.entity.TicketSoporte;
import com.Metro.org.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/comentarioadmin")
public class TicketAdminController {

    private final TicketService ticketService;

    public TicketAdminController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping({"/admin", "/admin/"})
    public String adminList(Model model) {
        model.addAttribute("comentarios", ticketService.getAll());
        return "ComentarioAdmin";
    }

    @GetMapping("/detalle/{id}")
    public String verDetalle(@PathVariable Integer id, Model model) {
        TicketSoporte ticket = ticketService.buscarPorId(id).orElse(null);
        if (ticket != null) {
            model.addAttribute("ticket", ticket);
            return "comentarioDetalle";
        }
        return "redirect:/comentarioadmin/admin/";
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
            ra.addFlashAttribute("mensajeError", "Error al actualizar: " + e.getMessage());
        }

        if ("detalle".equals(origen)) {
            return "redirect:/comentarioadmin/detalle/" + id;
        }
        return "redirect:/comentarioadmin/admin/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarTicket(@PathVariable Integer id, RedirectAttributes ra) {
        try {
            ticketService.delete(id);
            ra.addFlashAttribute("mensajeExito", "Ticket eliminado exitosamente.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el ticket.");
        }
        return "redirect:/comentarioadmin/admin/";
    }
}