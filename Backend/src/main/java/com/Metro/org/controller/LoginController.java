package com.Metro.org.controller;

import com.Metro.org.entity.Pasajero;
import com.Metro.org.entity.Personal;
import com.Metro.org.service.PasajeroService;
import com.Metro.org.service.PersonalService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

@Controller
public class LoginController {

    private final PersonalService personalService;
    private final PasajeroService pasajeroService;

    public LoginController(PersonalService personalService, PasajeroService pasajeroService) {
        this.personalService = personalService;
        this.pasajeroService = pasajeroService;
    }

    @GetMapping("/")
    public String index() {
        return "login";
    }

    @PostMapping("/login")
    public String autenticar(@RequestParam("email") String email,
                             @RequestParam("password") String password,
                             HttpSession session,
                             Model model) {
        Optional<Personal> usuarioOpt = personalService.findByEmail(email);
        if (usuarioOpt.isPresent() && usuarioOpt.get().getPassword().equals(password)) {
            session.setAttribute("nombreUsuario", usuarioOpt.get().getNombre());
            session.setAttribute("rolUsuario", usuarioOpt.get().getRol());
            return "redirect:/PaginaPrincipal";
        }
        model.addAttribute("error", "Credenciales incorrectas");
        return "login";
    }

    @GetMapping("/registro")
    public String mostrarRegistro() {
        return "registro";
    }

    @PostMapping("/registro")
    public String guardarPasajero(@RequestParam("nombre") String nombre,
                                  @RequestParam("tipo") String tipo,
                                  Model model) {
        try {
            Pasajero nuevoPasajero = new Pasajero();
            nuevoPasajero.setNombrePasajero(nombre);
            nuevoPasajero.setTipoPasajero(tipo);

            pasajeroService.savePasajero(nuevoPasajero);
            return "redirect:/?registroExito=true";
        } catch (Exception e) {
            model.addAttribute("error", "Error al registrar el pasajero");
            return "registro";
        }
    }

    @GetMapping("/PaginaPrincipal")
    public String mostrarPaginaPrincipal(HttpSession session, Model model) {
        if (session.getAttribute("nombreUsuario") == null) return "redirect:/";
        model.addAttribute("nombre", session.getAttribute("nombreUsuario"));
        model.addAttribute("rol", session.getAttribute("rolUsuario"));
        return "PaginaPrincipal";
    }
}