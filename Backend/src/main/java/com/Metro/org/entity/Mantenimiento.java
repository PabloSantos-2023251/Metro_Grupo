package com.Metro.org.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "mantenimiento")
public class Mantenimiento {

    @Id
    @Column(name = "id_mantenimiento")
    @NotNull(message = "El ID de mantenimiento es obligatorio")
    private Integer idMantenimiento;

    @Column(name = "fecha")
    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;

    @Column(name = "id_tren")
    @NotNull(message = "El ID del tren es obligatorio")
    private Integer idTren;

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