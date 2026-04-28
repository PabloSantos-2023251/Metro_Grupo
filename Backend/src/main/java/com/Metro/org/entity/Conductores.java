package com.Metro.org.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "conductores")
public class Conductores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conductor")
    private Integer idConductor;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Pattern(
            regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ]+\\s+[A-Za-zÁÉÍÓÚáéíóúÑñ]+.*$",
            message = "Debe ingresar nombre y apellido"
    )
    @Column(name = "nombre")
    private String nombre;

    @NotBlank(message = "La licencia no debe estar vacía")
    @Size(max = 50, message = "La licencia no puede exceder 50 caracteres")
    @Pattern(
            regexp = "^[A-Z0-9]{5,50}$",
            message = "La licencia debe contener solo letras mayúsculas y números (5-50 caracteres)"
    )
    @Column(name = "licencia", unique = true)
    private String licencia;

    @NotNull(message = "Los años de experiencia son obligatorios")
    @Min(value = 0, message = "La experiencia no puede ser negativa")
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