package com.Metro.org.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity // una clase anotada , represeta una tabla relacionar , actua comom un puente entre la programacion orientada a objetos y SQL (JPA ivernes)prermitiendo mapiar automaticamente atrivutos a columnas
@Table(name = "trenes")

public class trenes {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tren")
    private Integer idTren;
    @NotBlank(message = "El campo de texto no debe de estar vacio")
    @Column(name = "modelo")
    private String modelo;

    @NotNull(message = "El campo no puede estar vacio")
    @Column(name = "capacidad_pasajeros")
    private Integer capacidadPasajeros;

    @NotBlank(message = "El campo de texto no debe de estar vacio")
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

