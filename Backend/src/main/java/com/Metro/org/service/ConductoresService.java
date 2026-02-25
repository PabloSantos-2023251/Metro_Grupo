package com.Metro.org.service;

import com.Metro.org.entity.Conductores;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConductoresService {
    List<Conductores> getAllConductores();
    Conductores getConductoresById(Integer id);
    Conductores saveConductores (Conductores conductores) throws RuntimeException;
    void deleteConductores(Integer id);

    Conductores updateConductores(Integer id, Conductores conductores);
}
