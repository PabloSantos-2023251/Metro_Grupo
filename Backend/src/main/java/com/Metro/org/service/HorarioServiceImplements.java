package com.Metro.org.service;

import com.Metro.org.entity.Horario;
import com.Metro.org.repository.HorarioRepository;
import com.Metro.org.repository.TrenRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HorarioServiceImplements implements HorarioService {

    private final HorarioRepository horarioRepository;
    private final TrenRepository trenRepository;

    public HorarioServiceImplements(HorarioRepository horarioRepository, TrenRepository trenRepository) {
        this.horarioRepository = horarioRepository;
        this.trenRepository = trenRepository;
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
        validarLogicaHorario(horario);
        return horarioRepository.save(horario);
    }

    @Override
    public Horario updateHorario(Integer id, Horario horario) {
        validarLogicaHorario(horario);
        return horarioRepository.findById(id).map(existente -> {
            existente.setHoraSalida(horario.getHoraSalida());
            existente.setHoraLlegada(horario.getHoraLlegada());
            existente.setIdTren(horario.getIdTren());
            return horarioRepository.save(existente);
        }).orElse(null);
    }

    private void validarLogicaHorario(Horario h) {
        if (!trenRepository.existsById(h.getIdTren())) {
            throw new RuntimeException("El tren con ID " + h.getIdTren() + " no existe.");
        }

        if (h.getHoraSalida().isAfter(h.getHoraLlegada()) || h.getHoraSalida().equals(h.getHoraLlegada())) {
            throw new RuntimeException("La hora de salida debe ser anterior a la hora de llegada.");
        }

        List<Horario> traslapes = horarioRepository.buscarTraslapes(h.getIdTren(), h.getHoraSalida(), h.getHoraLlegada());

        for (Horario t : traslapes) {
            if (!t.getIdHorario().equals(h.getIdHorario())) {
                throw new RuntimeException("El Tren #" + h.getIdTren() + " ya tiene un viaje programado entre " + t.getHoraSalida() + " y " + t.getHoraLlegada());
            }
        }
    }

    @Override
    public boolean deleteHorario(Integer id) {
        if (horarioRepository.existsById(id)) {
            horarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Horario> getHorariosByTren(Integer idTren) {
        return horarioRepository.findByIdTren(idTren);
    }
}