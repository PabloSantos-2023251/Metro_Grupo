package com.Metro.org.entity;

import jakarta.persistence.*;

@Entity // una clase anotada , represeta una tabla relacionar , actua comom un puente entre la programacion orientada a objetos y SQL (JPA ivernes)prermitiendo mapiar automaticamente atrivutos a columnas
@Table(name = "conductores")

public class Conductores {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conductor")
    private Integer idConductor;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "Licencia")
    private Integer licencia;

    @Column(name = "anios_experencia")
    private String aniosExperencia;

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

    public Integer getLicencia() {
        return licencia;
    }

    public void setLicencia(Integer licencia) {
        this.licencia = licencia;
    }

    public String getAniosExperencia() {
        return aniosExperencia;
    }

    public void setAniosExperencia(String aniosExperencia) {
        this.aniosExperencia = aniosExperencia;
    }
}