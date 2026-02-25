package com.Metro.org.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "mantenimiento")
public class Mantenimiento {

    @Id
    @Column(name = "id_mantenimiento")
    private Integer idMantenimiento;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "id_tren")
    private Integer idTren;

    // Getters y Setters
    public Integer getIdMantenimiento() {
        return idMantenimiento;
    }

    public void setIdMantenimiento(Integer idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdTren() {
        return idTren;
    }

    public void setIdTren(Integer idTren) {
        this.idTren = idTren;
    }
}