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
        return horarioRepository.findById(id).map(existente -> {
            existente.setHoraSalida(horario.getHoraSalida());
            existente.setHoraLlegada(horario.getHoraLlegada());
            existente.setIdTren(horario.getIdTren());
            return horarioRepository.save(existente);
        }).orElse(null);
    }

    @Override
    public boolean deleteHorario(Integer id) {
        if (horarioRepository.existsById(id)) {
            horarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}