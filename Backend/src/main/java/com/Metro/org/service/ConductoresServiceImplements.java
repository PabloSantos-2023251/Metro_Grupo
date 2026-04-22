package com.Metro.org.service;


import com.Metro.org.entity.Conductores;
import com.Metro.org.repository.ConductoresRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ConductoresServiceImplements implements ConductoresService {

    private final ConductoresRepository conductoresRepository;

    public ConductoresServiceImplements(ConductoresRepository conductoresRepository) {
        this.conductoresRepository = conductoresRepository;
    }

    @Override
    public List<Conductores> getAllConductores() {
        return conductoresRepository.findAll();
    }

    @Override
    public Conductores getConductoresById(Integer id) {
        return conductoresRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conductor no encontrado con ID: " + id));
    }

    @Override
    public Conductores saveConductores(Conductores conductores) {
        return conductoresRepository.save(conductores);
    }

    @Override
    public void deleteConductores(Integer id) {
        if (!conductoresRepository.existsById(id)) {
            throw new RuntimeException("El conductor no existe con ID: " + id);
        }
        conductoresRepository.deleteById(id);
    }

    @Override
    public Conductores updateConductores(Integer id, Conductores conductores) {
        Conductores existing = conductoresRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El conductor no existe con ID: " + id));

        existing.setNombre(conductores.getNombre());
        existing.setLicencia(conductores.getLicencia());
        existing.setAniosExperiencia(conductores.getAniosExperiencia());

        return conductoresRepository.save(existing);
    }

}