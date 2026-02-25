package com.Metro.org.Entity;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "horarios")
public class Horario {

    @Id
    @Column(name = "id_horario")
    private Integer idHorario;

    @Column(name = "hora_salida")
    private LocalTime horaSalida;

    @Column(name = "hora_llegada")
    private LocalTime horaLlegada;

    @Column(name = "id_tren")
    private Integer idTren;

    // Getters y Setters
    public Integer getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public LocalTime getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(LocalTime horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public Integer getIdTren() {
        return idTren;
    }

    public void setIdTren(Integer idTren) {
        this.idTren = idTren;
    }
}