package com.Metro.org.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Linea")
public class Linea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_linea")
    private Integer idLinea;

    @NotBlank(message = "El nombre de la línea es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre de la línea debe tener entre 3 y 50 caracteres")
    @Column (name = "nombre_linea", nullable = false)
    private String nombreLinea;

    @NotBlank(message = "El color es obligatorio")
    @Size(min = 3, max = 30, message = "El color debe tener entre 3 y 30 caracteres")
    @Column (name = "color", nullable = false)
    private String color;

    @NotNull(message = "La longitud es obligatoria")
    @Positive(message = "La longitud debe ser un número positivo")
    @Column (name = "longitud_km", nullable = false)
    private Integer logitudKm;

    // generar getter an setter


    public Integer getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(Integer idLinea) {
        this.idLinea = idLinea;
    }

    public String getNombreLinea() {
        return nombreLinea;
    }

    public void setNombreLinea(String nombreLinea) {
        this.nombreLinea = nombreLinea;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getLogitudKm() {
        return logitudKm;
    }

    public void setLogitudKm(Integer logitudKm) {
        this.logitudKm = logitudKm;
    }
}
