package com.Metro.org.controller;

import com.Metro.org.entity.Boleto;
import com.Metro.org.entity.Pasajero;
import com.Metro.org.service.BoletoService;
import com.Metro.org.service.PasajeroService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Collections;
import java.time.LocalDate;
import java.math.BigDecimal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/boletos")
public class BoletoController {

    private final BoletoService boletoService;
    private final PasajeroService pasajeroService;

    public BoletoController(BoletoService boletoService, PasajeroService pasajeroService) {
        this.boletoService = boletoService;
        this.pasajeroService = pasajeroService;
    }

    private boolean esAdministrador(HttpSession session) {
        Object rol = session.getAttribute("rolUsuario");
        return rol != null && rol.toString().equalsIgnoreCase("administrador");
    }

    private Integer obtenerIdSesion(HttpSession session) {
        String[] nombresPosibles = {"idUsuario", "idPasajero", "usuarioId", "id"};
        for (String nombre : nombresPosibles) {
            Object id = session.getAttribute(nombre);
            if (id != null) {
                try { return Integer.parseInt(id.toString()); } catch (Exception e) {}
            }
        }
        return null;
    }

    @GetMapping({"", "/"})
    public String listar(HttpSession session, Model model) {
        Integer idUsuario = obtenerIdSesion(session);
        List<Boleto> lista = esAdministrador(session) ? boletoService.getAllBoletos() :
                (idUsuario != null ? boletoService.getBoletosByPasajero(idUsuario) : Collections.emptyList());

        model.addAttribute("boletos", lista);
        model.addAttribute("boletoForm", new Boleto());
        return "Boletos";
    }

    @PostMapping("/agregar")
    public String agregar(@RequestParam(value = "idPasajero", required = false) Integer idPasajeroForm,
                          HttpSession session, RedirectAttributes ra) {
        try {
            Integer idFinal = esAdministrador(session) ? idPasajeroForm : obtenerIdSesion(session);
            if (idFinal == null) throw new RuntimeException("ID de usuario no encontrado.");

            Pasajero pasajero = pasajeroService.getPasajeroById(idFinal);
            if (pasajero == null) throw new RuntimeException("Pasajero no existe.");

            Boleto boleto = new Boleto();
            boleto.setPasajero(pasajero);
            boleto.setFecha(LocalDate.now());
            boleto.setPrecio(new BigDecimal("10.00"));

            boletoService.saveBoleto(boleto);
            ra.addFlashAttribute("mensajeExito", "Boleto generado.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", e.getMessage());
        }
        return "redirect:/boletos/";
    }

    @GetMapping("/borrar/{id}")
    public String borrar(@PathVariable Integer id, HttpSession session) {
        if (esAdministrador(session)) boletoService.deleteBoleto(id);
        return "redirect:/boletos/";
    }
}