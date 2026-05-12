package com.Metro.org.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.FutureOrPresent;
import java.time.LocalDate;

@Entity
@Table(name = "mantenimiento")
public class Mantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mantenimiento")
    private Integer idMantenimiento;

    @Column(name = "fecha")
    @NotNull(message = "La fecha es obligatoria")
    @FutureOrPresent(message = "La fecha del mantenimiento no puede ser una fecha pasada")
    private LocalDate fecha;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(min = 10, message = "La descripción debe tener al menos 10 caracteres para ser válida")
    private String descripcion;

    @Column(name = "id_tren")
    @NotNull(message = "El ID del tren es obligatorio")
    private Integer idTren;

    public Mantenimiento() {}

    // Getters y Setters
    public Integer getIdMantenimiento() { return idMantenimiento; }
    public void setIdMantenimiento(Integer idMantenimiento) { this.idMantenimiento = idMantenimiento; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Integer getIdTren() { return idTren; }
    public void setIdTren(Integer idTren) { this.idTren = idTren; }
}