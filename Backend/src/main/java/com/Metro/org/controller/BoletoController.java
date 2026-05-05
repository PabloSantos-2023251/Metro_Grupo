package com.Metro.org.controller;

import com.Metro.org.entity.Boleto;
import com.Metro.org.entity.Pasajero;
import com.Metro.org.service.BoletoService;
import com.Metro.org.service.PasajeroService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

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
        String rolStr = rol.toString();
        return rolStr.equalsIgnoreCase("administrador") || rolStr.equalsIgnoreCase("admin");
    }

    private Integer obtenerIdSesion(HttpSession session) {
        Object id = session.getAttribute("idUsuario");
        if (id == null) return null;
        try {
            return Integer.parseInt(id.toString());
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping
    public String listar(@RequestParam(name = "buscarId", required = false) Integer buscarId,
                         HttpSession session, Model model) {

        List<Boleto> lista;

        if (esAdministrador(session)) {
            // Admin: ver todos o filtrar por ID pasajero
            lista = (buscarId != null)
                    ? boletoService.getBoletosByPasajero(buscarId)
                    : boletoService.getAllBoletos();
        } else {
            // Pasajero: solo sus propios boletos (ignora buscarId)
            Integer idUsuario = obtenerIdSesion(session);
            lista = (idUsuario != null)
                    ? boletoService.getBoletosByPasajero(idUsuario)
                    : Collections.emptyList();
        }

        model.addAttribute("boletos", lista);
        model.addAttribute("boletoForm", new Boleto());
        return "Boletos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormEditar(@PathVariable Integer id,
                                    HttpSession session, Model model) {
        if (!esAdministrador(session)) return "redirect:/boletos";

        Boleto boleto = boletoService.getBoletoById(id);
        List<Boleto> lista = boletoService.getAllBoletos();
        model.addAttribute("boletoForm", boleto);
        model.addAttribute("boletos", lista);
        return "Boletos";
    }

    @PostMapping("/agregar")
    public String agregar(@ModelAttribute("boletoForm") Boleto boleto,
                          @RequestParam(value = "idPasajero", required = false) Integer idPasajeroParam,
                          HttpSession session, Model model) {
        try {
            Integer idFinal = esAdministrador(session)
                    ? idPasajeroParam
                    : obtenerIdSesion(session);

            if (idFinal == null) {
                throw new RuntimeException("No se pudo recuperar el ID del pasajero.");
            }

            Pasajero pasajero = pasajeroService.getPasajeroById(idFinal);
            boleto.setPasajero(pasajero);
            boletoService.saveBoleto(boleto);
            return "redirect:/boletos";

        } catch (RuntimeException e) {
            model.addAttribute("errorBoleto", e.getMessage());
            model.addAttribute("boletos", boletoService.getAllBoletos());
            return "Boletos";
        }
    }

    @PostMapping("/editar/{id}")
    public String editar(@PathVariable Integer id,
                         @ModelAttribute("boletoForm") Boleto boleto,
                         @RequestParam("idPasajero") Integer idPasajero,
                         HttpSession session, Model model) {
        if (!esAdministrador(session)) return "redirect:/boletos";

        try {
            Pasajero pasajero = pasajeroService.getPasajeroById(idPasajero);
            boleto.setPasajero(pasajero);
            boletoService.updateBoleto(id, boleto);
            return "redirect:/boletos";

        } catch (RuntimeException e) {
            model.addAttribute("errorBoleto", e.getMessage());
            model.addAttribute("boletos", boletoService.getAllBoletos());
            return "Boletos";
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