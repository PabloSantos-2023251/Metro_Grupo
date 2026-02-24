package com.Metro.org.Service;


import com.Metro.org.Entity.Conductores;
import com.Metro.org.Repository.ConductoresRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConductoresServiceImplements implements ConductoresService {
    private final ConductoresRepository ConductoresRepository;


    public ConductoresServiceImplements(ConductoresRepository conductoresRepository) {
        ConductoresRepository = conductoresRepository;
    }

    @Override
    public List<Conductores> getAllConductores() {
        return ConductoresRepository.findAll();
    }

    @Override
    public Conductores getConductoresById(Integer id) {
        return ConductoresRepository.findById(id).orElse(null);
    }

    @Override
    public Conductores saveConductores(Conductores conductores) throws RuntimeException {
        return ConductoresRepository.save(conductores);
    }


    @Override
    public void deleteConductores(Integer id) {
        if (!ConductoresRepository.existsById(id)) {
            throw new RuntimeException("Conductores no existe");
        }
        ConductoresRepository.deleteById(id);
    }

    @Override
    public Conductores updateConductores(Integer id, Conductores conductores) {
        Conductores existingConductores = ConductoresRepository.findById(id).orElseThrow(() -> new RuntimeException("El Conductores no existe"));

        existingConductores.setNombre(conductores.getNombre());
        existingConductores.setLicencia(conductores.getLicencia());
        existingConductores.setAnosExperencia(conductores.getAnosExperencia());

        return ConductoresRepository.save(existingConductores);
    }
}