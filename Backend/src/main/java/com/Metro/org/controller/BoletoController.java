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
        if (rol == null) return false;
        return rol.toString().equalsIgnoreCase("administrador");
    }

    private Integer obtenerIdSesion(HttpSession session) {
        String[] nombresPosibles = {"idUsuario", "idPasajero", "usuarioId", "id"};
        for (String nombre : nombresPosibles) {
            Object id = session.getAttribute(nombre);
            if (id != null) {
                try {
                    return Integer.parseInt(id.toString());
                } catch (Exception e) {
                    continue;
                }
            }
        }
        return null;
    }

    @GetMapping
    public String listar(HttpSession session, Model model) {
        Integer idUsuario = obtenerIdSesion(session);
        List<Boleto> lista;

        if (esAdministrador(session)) {
            lista = boletoService.getAllBoletos();
        } else {
            lista = (idUsuario != null) ? boletoService.getBoletosByPasajero(idUsuario) : Collections.emptyList();
        }

        model.addAttribute("boletos", lista);
        model.addAttribute("boletoForm", new Boleto());
        return "Boletos";
    }

    @PostMapping("/agregar")
    public String agregar(@RequestParam(value = "idPasajero", required = false) Integer idPasajeroForm,
                          HttpSession session, Model model) {
        try {
            Integer idFinal;
            if (esAdministrador(session)) {
                idFinal = idPasajeroForm;
            } else {
                idFinal = obtenerIdSesion(session);
            }

            if (idFinal == null) {
                throw new RuntimeException("Error: No se pudo recuperar tu ID de usuario.");
            }

            Pasajero pasajero = pasajeroService.getPasajeroById(idFinal);
            if (pasajero == null) {
                throw new RuntimeException("El pasajero con ID " + idFinal + " no existe.");
            }

            Boleto boleto = new Boleto();
            boleto.setPasajero(pasajero);
            boleto.setFecha(LocalDate.now());

            String tipo = (pasajero.getTipo() != null) ? pasajero.getTipo() : "General";
            if (tipo.equalsIgnoreCase("Adulto Mayor") || tipo.equalsIgnoreCase("Discapacitado")) {
                boleto.setPrecio(BigDecimal.ZERO);
            } else {
                boleto.setPrecio(new BigDecimal("10.00"));
            }

            boletoService.saveBoleto(boleto);
            return "redirect:/boletos";
        } catch (Exception e) {
            model.addAttribute("errorBoleto", e.getMessage());
            return listar(session, model);
        }
    }

    @GetMapping("/borrar/{id}")
    public String borrar(@PathVariable Integer id, HttpSession session) {
        if (esAdministrador(session)) {
            boletoService.deleteBoleto(id);
        }
        return "redirect:/boletos";
    }
}