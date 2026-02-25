package com.Metro.org.Service;


import com.Metro.org.Entity.conductores;
import com.Metro.org.Repository.conductoresRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class conductoresServiceImplements implements conductoresService {
    private final conductoresRepository ConductoresRepository;


    public conductoresServiceImplements(conductoresRepository conductoresRepository) {
        ConductoresRepository = conductoresRepository;
    }

    @Override
    public List<conductores> getAllConductores() {
        return ConductoresRepository.findAll();
    }

    @Override
    public conductores getConductoresById(Integer id) {
        return ConductoresRepository.findById(id).orElse(null);
    }

    @Override
    public conductores saveConductores(conductores conductores) throws RuntimeException {
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
    public conductores updateConductores(Integer id, conductores conductores) {
        conductores existingConductores = ConductoresRepository.findById(id).orElseThrow(() -> new RuntimeException("El Conductores no existe"));

        existingConductores.setNombre(conductores.getNombre());
        existingConductores.setLicencia(conductores.getLicencia());
        existingConductores.setAnosExperencia(conductores.getAnosExperencia());

        return ConductoresRepository.save(existingConductores);
    }
}