package com.Metro.org.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Pasajeros")
public class Pasajero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pasajero")
    private Integer idPasajero;

    @NotBlank(message = "El nombre del pasajero es obligatorio")
    @Column(name = "nombre_pasajero", nullable = false, length = 50)
    private String nombrePasajero;

    @NotBlank(message = "El tipo de pasajero es obligatorio")
    @Column(name = "tipo_pasajero", nullable = false, length = 30)
    private String tipoPasajero;

    @Column(name = "email", unique = true, length = 100)
    private String email;

    @Column(name = "password", length = 100)
    private String password;

    // Getters y Setters
    public Integer getIdPasajero() { return idPasajero; }
    public void setIdPasajero(Integer idPasajero) { this.idPasajero = idPasajero; }
    public String getNombrePasajero() { return nombrePasajero; }
    public void setNombrePasajero(String nombrePasajero) { this.nombrePasajero = nombrePasajero; }
    public String getTipoPasajero() { return tipoPasajero; }
    public void setTipoPasajero(String tipoPasajero) { this.tipoPasajero = tipoPasajero; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNombre() { return nombrePasajero; }
    public void setNombre(String nombre) { this.nombrePasajero = nombre; }
    public String getTipo() { return tipoPasajero; }
    public void setTipo(String tipo) { this.tipoPasajero = tipo; }
}