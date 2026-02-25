package com.Metro.org.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Linea")
public class Linea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_linea")
    private Integer idLinea;

    @Column (name = "nombre_linea")
    private String nombreLinea;

    @Column (name = "color")
    private String color;

    @Column (name = "longitud_km")
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
