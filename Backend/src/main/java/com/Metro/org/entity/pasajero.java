package com.Metro.org.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Pasajeros")
public class pasajero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pasajero")
    private Integer idPasajero;

    @Column(name = "nombre_pasajero", nullable = false, length = 50)
    private String nombrePasajero;

    @Column(name = "tipo_pasajero", nullable = false, length = 30)
    private String tipoPasajero;

    // Getters y Setters //

    public Integer getIdPasajero() {
        return idPasajero;
    }

    public void setIdPasajero(Integer idPasajero) {
        this.idPasajero = idPasajero;
    }

    public String getNombrePasajero() {
        return nombrePasajero;
    }

    public void setNombrePasajero(String nombrePasajero) {
        this.nombrePasajero = nombrePasajero;
    }

    public String getTipoPasajero() {
        return tipoPasajero;
    }

    public void setTipoPasajero(String tipoPasajero) {
        this.tipoPasajero = tipoPasajero;
    }
}