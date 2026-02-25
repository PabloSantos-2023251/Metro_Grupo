package com.Metro.org.Service;

import com.Metro.org.Entity.conductores;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface conductoresService {
    List<conductores> getAllConductores();
    conductores getConductoresById(Integer id);
    conductores saveConductores (conductores conductores) throws RuntimeException;
    void deleteConductores(Integer id);

    conductores updateConductores(Integer id, conductores conductores);
}
