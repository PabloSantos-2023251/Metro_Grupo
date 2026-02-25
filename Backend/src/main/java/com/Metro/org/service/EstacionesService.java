package com.Metro.org.service;

import com.Metro.org.entity.Estaciones;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface EstacionesService {
    List<Estaciones> getAllEstaciones();
    Estaciones getEstacionesById(Integer id);
    Estaciones saveEstaciones (Estaciones estaciones) throws RuntimeException;
    Estaciones updateEstaciones (Integer id, Estaciones estaciones);
    void deleteEstaciones(Integer id);
}
