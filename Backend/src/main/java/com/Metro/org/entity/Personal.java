package com.Metro.org.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "personal")
public class Personal {
    @Id
    @Column(name = "id_personal")
    private Integer id_personal;

    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String cargo;

    @Column(nullable = false)
    private String rol;

    // Getters y Setters
    public Integer getId_personal() { return id_personal; }
    public void setId_personal(Integer id_personal) { this.id_personal = id_personal; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}