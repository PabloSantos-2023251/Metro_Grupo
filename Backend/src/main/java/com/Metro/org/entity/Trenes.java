package com.Metro.org.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "trenes")
public class Trenes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tren")
    private Integer idTren;

    @NotBlank(message = "El modelo no puede estar vacío")
    @Column(name = "modelo")
    private String modelo;

    @NotNull(message = "La capacidad no puede estar vacía")
    @Min(value = 50, message = "La capacidad debe ser al menos 50 pasajeros")
    @Column(name = "capacidad_pasajeros")
    private Integer capacidadPasajeros;

    @NotBlank(message = "El estado no puede estar vacío")
    @Pattern(
            regexp = "(?i)Operativo|Mantenimiento|Fuera de Servicio",
            message = "Estado inválido (Operativo, Mantenimiento o Fuera de Servicio)"
    )
    @Column(name = "estado")
    private String estado;

    public Integer getIdTren() {
        return idTren;
    }

    public void setIdTren(Integer idTren) {
        this.idTren = idTren;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public void setCapacidadPasajeros(Integer capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}