package com.Metro.org.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "conductores")
public class Conductores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conductor")
    private Integer idConductor;

    @NotBlank(message = "El campo de texto no debe de estar vacio")
    @Column(name = "nombre")
    private String nombre;

    @NotBlank(message = "La licencia no debe estar vacía")
    @Size(max = 50, message = "La licencia no puede exceder de 50 caracteres")
    @Column(name = "licencia")
    private String licencia;

    @NotNull(message = "Los años de experiencia son obligatorios")
    @Column(name = "anos_experiencia")
    private Integer aniosExperiencia;

    public Integer getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(Integer idConductor) {
        this.idConductor = idConductor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public Integer getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(Integer aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }
}