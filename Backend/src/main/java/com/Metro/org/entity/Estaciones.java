package com.Metro.org.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Estaciones")
public class Estaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estacion")
    private Integer idEstacion;

    @Column(name = "nombre")
    private String nombre;

    @Column (name = "zona")
    private String zona;

    @Column(name = "id_linea")
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