package com.Metro.org.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "personal")
public class Personal {
    @Id
    @Column(name = "id_personal")
    private Integer id_personal; // Nota: No tiene Auto-increment en tu SQL

    private String nombre;
    private String cargo;

    // Getters y Setters
    public Integer getId_personal() { return id_personal; }
    public void setId_personal(Integer id_personal) { this.id_personal = id_personal; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
}