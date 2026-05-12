package com.Metro.org.controller;

import com.Metro.org.entity.TicketSoporte;
import com.Metro.org.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/ticket")
    public String mostrarSoporte() {
        return "Soporte";
    }

    @PostMapping("/guardarTicket")
    public String enviarTicket(
            @RequestParam("nombreCompleto") String nombre,
            @RequestParam("email") String email,
            @RequestParam("tipoConsulta") String tipo,
            @RequestParam("mensaje") String mensaje) {

        TicketSoporte ticket = new TicketSoporte();
        ticket.setNombreCompleto(nombre);
        ticket.setEmail(email);
        ticket.setTipoConsulta(TicketSoporte.TipoConsulta.valueOf(tipo));
        ticket.setMensaje(mensaje);

        ticketService.save(ticket);
        return "redirect:/ticket?exito";
    }
}