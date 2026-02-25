package com.Metro.org.Service;

import com.Metro.org.Entity.Horario;
import java.util.List;

public interface HorarioService {
    List<Horario> getAllHorarios();
    Horario getHorarioById(Integer id);
    Horario saveHorario(Horario horario);
    Horario updateHorario(Integer id, Horario horario);
    boolean deleteHorario(Integer id); // Cambiado a boolean
}