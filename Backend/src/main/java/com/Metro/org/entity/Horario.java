package com.Metro.org.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalTime;

@Entity
@Table(name = "horarios")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Se agregó para que el ID sea automático
    @Column(name = "id_horario")
    private Integer idHorario;

    @Column(name = "hora_salida")
    @NotNull(message = "La hora de salida es obligatoria")
    private LocalTime horaSalida;

    @Column(name = "hora_llegada")
    @NotNull(message = "La hora de llegada es obligatoria")
    private LocalTime horaLlegada;

    @Column(name = "id_tren")
    @NotNull(message = "El ID del tren es obligatorio")
    @Positive(message = "El ID del tren debe ser un número positivo")
    private Integer idTren;

    // Constructores
    public Horario() {
    }

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