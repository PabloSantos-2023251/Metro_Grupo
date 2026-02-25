package com.Metro.org.entity;

import jakarta.persistence.*;

@Entity // una clase anotada , represeta una tabla relacionar , actua comom un puente entre la programacion orientada a objetos y SQL (JPA ivernes)prermitiendo mapiar automaticamente atrivutos a columnas
@Table(name = "Conductores")

public class Conductores {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Conductores")
    private Integer idConductores;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "licencia")
    private Integer licencia;

    @Column(name = "anos_experencia")
    private String anosExperencia;

    public Integer getIdConductores() {
        return idConductores;
    }

    public void setIdConductores(Integer idConductores) {
        this.idConductores = idConductores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAnosExperencia() {
        return anosExperencia;
    }

    public void setAnosExperencia(String anosExperencia) {
        this.anosExperencia = anosExperencia;
    }

    public Integer getLicencia() {
        return licencia;
    }

    public void setLicencia(Integer licencia) {
        this.licencia = licencia;
    }
}
