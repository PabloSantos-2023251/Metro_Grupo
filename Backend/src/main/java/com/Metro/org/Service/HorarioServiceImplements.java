package com.Metro.org.Service;

import com.Metro.org.Entity.Horario;
import com.Metro.org.Repository.HorarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HorarioServiceImplements implements HorarioService {

    private final HorarioRepository horarioRepository;

    public HorarioServiceImplements(HorarioRepository horarioRepository) {
        this.horarioRepository = horarioRepository;
    }

    @Override
    public List<Horario> getAllHorarios() {
        return horarioRepository.findAll();
    }

    @Override
    public Horario getHorarioById(Integer id) {
        return horarioRepository.findById(id).orElse(null);
    }

    @Override
    public Horario saveHorario(Horario horario) {
        return horarioRepository.save(horario);
    }

    @Override
    public Horario updateHorario(Integer id, Horario horario) {
        Horario existente = horarioRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setHoraSalida(horario.getHoraSalida());
            existente.setHoraLlegada(horario.getHoraLlegada());
            existente.setIdTren(horario.getIdTren());
            return horarioRepository.save(existente);
        }
        return null;
    }

    @Override
    public void deleteHorario(Integer id) {
        horarioRepository.deleteById(id);
    }
}