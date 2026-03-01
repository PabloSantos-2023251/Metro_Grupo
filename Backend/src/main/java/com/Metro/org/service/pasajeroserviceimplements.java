package com.Metro.org.service;

import com.Metro.org.entity.pasajero;
import com.Metro.org.repository.pasajerorepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public  class pasajeroserviceimplements implements pasajeroservice {
    private final pasajerorepository pasajeroRepository;

    public pasajeroserviceimplements(pasajerorepository pasajeroRepository) {
        this.pasajeroRepository = pasajeroRepository;
    }


    @Override
    public List<pasajero> getAllPasajeros() {
        return pasajeroRepository.findAll();
    }

    @Override
    public pasajero getPasajeroById(Integer id) {
        return pasajeroRepository.findById(id).orElse(null);
    }

    @Override
    public pasajero savePasajero(pasajero pasajero) throws RuntimeException {
        return pasajeroRepository.save(pasajero);
    }

    @Override
    public pasajero updatePasajero(Integer id, pasajero pasajero) {
        Optional<pasajero> pasajeroExistente = pasajeroRepository.findById(id);
        if (pasajeroExistente.isPresent()) {
            pasajero newPasajero = pasajeroExistente.get();
            newPasajero.setIdPasajero(pasajero.getIdPasajero());
            newPasajero.setTipoPasajero(pasajero.getTipoPasajero());
            newPasajero.setNombrePasajero(pasajero.getNombrePasajero());
            return pasajeroRepository.save(newPasajero);
        } else {
            return null;
        }
    }

    @Override
    public void deletePasajero(Integer id){pasajeroRepository.deleteById(id);}
}
