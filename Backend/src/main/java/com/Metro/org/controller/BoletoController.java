package com.Metro.org.controller;

import com.Metro.org.entity.Boleto;
import com.Metro.org.entity.Pasajero;
import com.Metro.org.service.BoletoService;
import com.Metro.org.service.PasajeroService;
import java.util.List;
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

    @GetMapping
    public String listar(@RequestParam(name = "buscarId", required = false) Integer buscarId, Model model) {
        List<Boleto> lista = (buscarId != null)
                ? boletoService.getBoletosByPasajero(buscarId)
                : boletoService.getAllBoletos();
        model.addAttribute("boletos", lista);
        model.addAttribute("boletoForm", new Boleto());
        return "Boletos";
    }

    // ← MÉTODO NUEVO: carga el formulario con los datos del boleto a editar
    @GetMapping("/editar/{id}")
    public String mostrarFormEditar(@PathVariable Integer id, Model model) {
        Boleto boleto = boletoService.getBoletoById(id);
        List<Boleto> lista = boletoService.getAllBoletos();
        model.addAttribute("boletoForm", boleto);
        model.addAttribute("boletos", lista);
        return "Boletos";
    }

    @PostMapping("/agregar")
    public String agregar(@ModelAttribute("boletoForm") Boleto boleto,
                          @RequestParam("idPasajero") Integer idPasajero) {
        Pasajero pasajero = pasajeroService.getPasajeroById(idPasajero);
        boleto.setPasajero(pasajero);
        boletoService.saveBoleto(boleto);
        return "redirect:/boletos";
    }

    @PostMapping("/editar/{id}")
    public String editar(@PathVariable Integer id,
                         @ModelAttribute("boletoForm") Boleto boleto,
                         @RequestParam("idPasajero") Integer idPasajero) {
        Pasajero pasajero = pasajeroService.getPasajeroById(idPasajero);
        boleto.setPasajero(pasajero);
        boletoService.updateBoleto(id, boleto);
        return "redirect:/boletos";
    }

    @GetMapping("/borrar/{id}")
    public String borrar(@PathVariable Integer id) {
        boletoService.deleteBoleto(id);
        return "redirect:/boletos";
    }
}