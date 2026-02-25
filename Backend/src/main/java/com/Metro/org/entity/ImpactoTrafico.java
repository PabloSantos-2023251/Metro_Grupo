package com.Metro.org.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "impacto_trafico")
public class ImpactoTrafico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_impacto")
    private Integer id_impacto;

    @Column(name = "zona")
    private String zona;

    @Column(name = "reduccion_trafico_porcentaje")
    private BigDecimal reduccionTraficoPorcentaje;

    // Getters y Setters
    public Integer getId_impacto() { return id_impacto; }
    public void setId_impacto(Integer id_impacto) { this.id_impacto = id_impacto; }
    public String getZona() { return zona; }
    public void setZona(String zona) { this.zona = zona; }
    public BigDecimal getReduccionTraficoPorcentaje() { return reduccionTraficoPorcentaje; }
    public void setReduccionTraficoPorcentaje(BigDecimal reduccionTraficoPorcentaje) { this.reduccionTraficoPorcentaje = reduccionTraficoPorcentaje; }
}