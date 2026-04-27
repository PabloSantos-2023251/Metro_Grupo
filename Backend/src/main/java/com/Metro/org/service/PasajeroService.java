package com.Metro.org.service;

import com.Metro.org.entity.Pasajero;
import java.util.List;

public interface PasajeroService {
    List<Pasajero> getAllPasajeros();
    Pasajero getPasajeroById(Integer id);
    Pasajero savePasajero(Pasajero pasajero);
    Pasajero updatePasajero(Integer id, Pasajero pasajero);
    void deletePasajero(Integer id);
}