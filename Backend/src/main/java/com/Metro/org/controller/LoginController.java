package com.Metro.org.controller;

<<<<<<< HEAD
import com.Metro.org.entity.Personal;
=======
import com.Metro.org.entity.Pasajero;
import com.Metro.org.entity.Personal;
import com.Metro.org.service.PasajeroService;
>>>>>>> ft-MiguelSantizo2022021
import com.Metro.org.service.PersonalService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

=======
import org.springframework.web.bind.annotation.*;
>>>>>>> ft-MiguelSantizo2022021
import java.util.Optional;

@Controller
public class LoginController {

    private final PersonalService personalService;
<<<<<<< HEAD

    public LoginController(PersonalService personalService) {
        this.personalService = personalService;
    }

    @GetMapping("/")
    public String index() {
=======
    private final PasajeroService pasajeroService;

    public LoginController(PersonalService personalService, PasajeroService pasajeroService) {
        this.personalService = personalService;
        this.pasajeroService = pasajeroService;
    }

    @GetMapping({"/", "/Principal"})
    public String index() {
        return "Principal";
    }

    @GetMapping("/modelos")
    public String mostrarModelos() {
        return "modelos";
    }

    @GetMapping("/Soporte")
    public String mostrarSoporte() {
        return "Soporte";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
>>>>>>> ft-MiguelSantizo2022021
        return "login";
    }

    @PostMapping("/login")
    public String autenticar(@RequestParam("email") String email,
                             @RequestParam("password") String password,
                             HttpSession session,
                             Model model) {

<<<<<<< HEAD
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
=======
        Optional<Personal> personalOpt = personalService.findByEmail(email);
        if (personalOpt.isPresent() && personalOpt.get().getPassword().equals(password)) {
            Personal p = personalOpt.get();
            session.setAttribute("idUsuario", p.getId_personal());
            session.setAttribute("nombreUsuario", p.getNombre());
            session.setAttribute("rolUsuario", p.getRol());
            session.setAttribute("puestoUsuario", p.getCargo());
            return "redirect:/PaginaPrincipal";
        }

        Optional<Pasajero> pasajeroOpt = pasajeroService.findByEmail(email);
        if (pasajeroOpt.isPresent() && pasajeroOpt.get().getPassword().equals(password)) {
            Pasajero pas = pasajeroOpt.get();
            session.setAttribute("idUsuario", pas.getIdPasajero());
            session.setAttribute("nombreUsuario", pas.getNombrePasajero());
            session.setAttribute("rolUsuario", "PASAJERO");
            session.setAttribute("puestoUsuario", "Cliente");
            return "redirect:/PaginaPrincipal";
        }

        model.addAttribute("error", "Correo o contraseña incorrectos");
>>>>>>> ft-MiguelSantizo2022021
        return "login";
    }

    @GetMapping("/PaginaPrincipal")
    public String mostrarPaginaPrincipal(HttpSession session, Model model) {
        if (session.getAttribute("nombreUsuario") == null) {
<<<<<<< HEAD
            return "redirect:/";
=======
            return "redirect:/login";
>>>>>>> ft-MiguelSantizo2022021
        }

        model.addAttribute("nombre", session.getAttribute("nombreUsuario"));
        model.addAttribute("rol", session.getAttribute("rolUsuario"));
<<<<<<< HEAD

        return "PaginaPrincipal";
    }
=======
        model.addAttribute("puesto", session.getAttribute("puestoUsuario"));
        return "PaginaPrincipal";
    }

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("pasajero", new Pasajero());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarPasajero(@ModelAttribute("pasajero") Pasajero pasajero) {
        pasajeroService.savePasajero(pasajero);
        return "redirect:/login?success";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/Principal";
    }
>>>>>>> ft-MiguelSantizo2022021
}