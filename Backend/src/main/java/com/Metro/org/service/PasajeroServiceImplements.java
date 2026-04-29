package com.Metro.org.service;

import com.Metro.org.entity.Pasajero;
import com.Metro.org.repository.PasajeroRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.Optional;

@Service
public class PasajeroServiceImplements implements PasajeroService {

    private final PasajeroRepository pasajeroRepository;
    private final PasswordEncoder passwordEncoder;

    public PasajeroServiceImplements(PasajeroRepository pasajeroRepository, PasswordEncoder passwordEncoder) {
        this.pasajeroRepository = pasajeroRepository;
        this.passwordEncoder = passwordEncoder;
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
        if (pasajero.getPassword() != null && !pasajero.getPassword().isEmpty()) {
            pasajero.setPassword(passwordEncoder.encode(pasajero.getPassword()));
        }
        return pasajeroRepository.save(pasajero);
    }

    @Override
    public Pasajero updatePasajero(Integer id, Pasajero pasajero) {
        return pasajeroRepository.findById(id).map(existente -> {
            existente.setNombrePasajero(pasajero.getNombrePasajero());
            existente.setTipoPasajero(pasajero.getTipoPasajero());
            existente.setEmail(pasajero.getEmail());

            if (pasajero.getPassword() != null && !pasajero.getPassword().isEmpty()) {
                existente.setPassword(passwordEncoder.encode(pasajero.getPassword()));
            }

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

    @Override
    public Optional<Pasajero> findByEmail(String email) {
        return pasajeroRepository.findByEmail(email);
    }
}