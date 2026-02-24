package com.Metro.org.Service;

import com.Metro.org.Entity.Conductores;
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
