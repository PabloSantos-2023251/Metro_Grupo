package com.Metro.org.service;

import com.Metro.org.entity.Pasajero;
import com.Metro.org.repository.PasajeroRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PasajeroServiceImplements implements PasajeroService {

    private final PasajeroRepository pasajeroRepository;

    public PasajeroServiceImplements(PasajeroRepository pasajeroRepository) {
        this.pasajeroRepository = pasajeroRepository;
    }

    @Override
    public List<Pasajero> getAllPasajeros() {
        return pasajeroRepository.findAll();
    }

    @Override
    public Pasajero getPasajeroById(Integer id) {
        return pasajeroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pasajero no encontrado"));
    }

    @Override
    public Pasajero savePasajero(Pasajero pasajero) {
        return pasajeroRepository.save(pasajero);
    }

    @Override
    public Pasajero updatePasajero(Integer id, Pasajero pasajero) {
        return pasajeroRepository.findById(id).map(existente -> {
            existente.setNombrePasajero(pasajero.getNombrePasajero());
            existente.setTipoPasajero(pasajero.getTipoPasajero());
            return pasajeroRepository.save(existente);
        }).orElseThrow(() -> new RuntimeException("No se encontró el pasajero"));
    }

    @Override
    public void deletePasajero(Integer id) {
        if (!pasajeroRepository.existsById(id)) {
            throw new RuntimeException("Pasajero no encontrado");
        }
        pasajeroRepository.deleteById(id);
    }
}
