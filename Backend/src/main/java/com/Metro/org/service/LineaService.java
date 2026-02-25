package com.DavidQuintanilla.metroSubterraneo.service;

import com.DavidQuintanilla.metroSubterraneo.entity.Linea;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public interface LineaService {
    List<Linea> getAllLinea();
    Linea getLineaById(Integer id);
    Linea saveLinea(Linea linea) throws RuntimeException;
    Linea updateLinea (Integer id, Linea linea);
    void deleteLinea (Integer id);
}