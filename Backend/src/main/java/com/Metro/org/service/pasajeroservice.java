package com.Metro.org.service;

import com.Metro.org.entity.pasajero;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface pasajeroservice {
    List<pasajero> getAllPasajeros();

    pasajero getPasajeroById(Integer id);
    pasajero savePasajero (pasajero pasajero) throws RuntimeException;
    pasajero updatePasajero(Integer id, pasajero pasajero);
    void deletePasajero(Integer id);

}
