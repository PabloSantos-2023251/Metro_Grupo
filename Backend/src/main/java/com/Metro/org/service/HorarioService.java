package com.Metro.org.service;

import com.Metro.org.entity.Horario;
import java.util.List;

public interface HorarioService {
    List<Horario> getAllHorarios();
    Horario getHorarioById(Integer id);
    Horario saveHorario(Horario horario);
    Horario updateHorario(Integer id, Horario horario);
    boolean deleteHorario(Integer id); // Cambiado a boolean
}