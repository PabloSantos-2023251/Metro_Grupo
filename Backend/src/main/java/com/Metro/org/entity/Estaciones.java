package com.Metro.org.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Estaciones")
public class Estaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estacion")
    private Integer idEstacion;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    @Column(name = "nombre",nullable = false)
    private String nombre;

    @NotBlank(message = "La zona es obligatoria")
    @Size(min = 1, max = 30, message = "La zona debe tener máximo 30 caracteres")
    @Column (name = "zona",nullable = false)
    private String zona;

    @NotNull(message = "El id de la línea es obligatorio")
    @Positive(message = "El id de la línea debe ser un número positivo")
    @Column(name = "id_linea",nullable = false)
    private Integer idLinea;

    // generar getter an setter


    public Integer getIdEstacion() {
        return idEstacion;
    }

    public void setIdEstacion(Integer idEstacion) {
        this.idEstacion = idEstacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public Integer getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(Integer idLinea) {
        this.idLinea = idLinea;
    }
}