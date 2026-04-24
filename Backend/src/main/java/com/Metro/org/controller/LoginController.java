package com.Metro.org.controller;

import com.Metro.org.entity.Personal;
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

    public LoginController(PersonalService personalService) {
        this.personalService = personalService;
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

        System.out.println("Intentando login con: " +email);

        Optional<Personal> usuarioOpt = personalService.findByEmail(email);

        if (usuarioOpt.isPresent()) {
            Personal u = usuarioOpt.get();
            System.out.println("Usuario encontrado en DB. Password real: "+u.getPassword());

            if (u.getPassword().equals(password)) {
                session.setAttribute("nombreUsuario",u.getNombre());
                session.setAttribute("rolUsuario",u.getRol());
                return "redirect:/PaginaPrincipal";
            } else {
                System.out.println("La contraseña no coincide.");
            }
        } else {
            System.out.println("No se encontró ningún usuario con ese correo.");
        }

        model.addAttribute("error", "Credenciales incorrectas");
        return "login";
    }

    @GetMapping("/PaginaPrincipal")
    public String mostrarPaginaPrincipal(HttpSession session, Model model) {
        if (session.getAttribute("nombreUsuario") == null) {
            return "redirect:/";
        }

        model.addAttribute("nombre", session.getAttribute("nombreUsuario"));
        model.addAttribute("rol", session.getAttribute("rolUsuario"));

        return "PaginaPrincipal";
    }
}