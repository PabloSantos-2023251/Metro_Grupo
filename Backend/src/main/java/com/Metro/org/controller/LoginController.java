package com.Metro.org.controller;

import com.Metro.org.entity.Pasajero;
import com.Metro.org.entity.Personal;
import com.Metro.org.service.PasajeroService;
import com.Metro.org.service.PersonalService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
public class LoginController {

    private final PersonalService personalService;
    private final PasajeroService pasajeroService;

    public LoginController(PersonalService personalService, PasajeroService pasajeroService) {
        this.personalService = personalService;
        this.pasajeroService = pasajeroService;
    }

    @GetMapping({"/", "/login"})
    public String index() {
        return "login";
    }

    @PostMapping("/login")
    public String autenticar(@RequestParam("email") String email,
                             @RequestParam("password") String password,
                             HttpSession session,
                             Model model) {

        Optional<Personal> personalOpt = personalService.findByEmail(email);
        if (personalOpt.isPresent() && personalOpt.get().getPassword().equals(password)) {
            session.setAttribute("nombreUsuario", personalOpt.get().getNombre());
            session.setAttribute("rolUsuario", personalOpt.get().getRol());
            return "redirect:/PaginaPrincipal";
        }

        Optional<Pasajero> pasajeroOpt = pasajeroService.findByEmail(email);
        if (pasajeroOpt.isPresent() && pasajeroOpt.get().getPassword().equals(password)) {
            session.setAttribute("nombreUsuario", pasajeroOpt.get().getNombrePasajero());
            session.setAttribute("rolUsuario", "PASAJERO");
            return "redirect:/PaginaPrincipal";
        }

        model.addAttribute("error", "Correo o contraseña incorrectos");
        return "login";
    }

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("pasajero", new Pasajero());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarPasajero(@ModelAttribute("pasajero") Pasajero pasajero) {
        // Guardamos al pasajero usando el service
        pasajeroService.savePasajero(pasajero);
        // Redirigimos al login con un mensaje de éxito
        return "redirect:/login?success";
    }

    @GetMapping("/PaginaPrincipal")
    public String mostrarPaginaPrincipal(HttpSession session, Model model) {
        if (session.getAttribute("nombreUsuario") == null) return "redirect:/login";
        model.addAttribute("nombre", session.getAttribute("nombreUsuario"));
        model.addAttribute("rol", session.getAttribute("rolUsuario"));
        return "PaginaPrincipal";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}