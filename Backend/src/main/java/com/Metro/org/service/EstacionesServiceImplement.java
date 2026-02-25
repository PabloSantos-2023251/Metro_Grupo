package com.DavidQuintanilla.metroSubterraneo.service;

import com.DavidQuintanilla.metroSubterraneo.entity.Estaciones;
import com.DavidQuintanilla.metroSubterraneo.repository.EstacionesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstacionesServiceImplement implements EstacionesService {
    private final EstacionesRepository estacionesRepository;

    public EstacionesServiceImplement(EstacionesRepository estacionesRepository){
        this.estacionesRepository = estacionesRepository;
    }

    @Override
    public List<Estaciones> getAllEstaciones(){
        return estacionesRepository.findAll();
    }

    @Override
    public Estaciones getEstacionesById(Integer id){
        return estacionesRepository.findById(id).orElse(null);
    }

    @Override
    public Estaciones saveEstaciones(Estaciones estaciones) throws RuntimeException{
        return estacionesRepository.save(estaciones);
    }

    @Override
    public Estaciones updateEstaciones (Integer id, Estaciones estacionesDetalles){

        Estaciones estacionesExistente = estacionesRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Estacion no encontrada por el ID:"+id));
        estacionesExistente.setNombre(estacionesDetalles.getNombre());
        estacionesExistente.setZona(estacionesDetalles.getZona());
        estacionesExistente.setIdLinea(estacionesDetalles.getIdLinea());

        return estacionesRepository.save(estacionesExistente);
    }

    @Override
    public void deleteEstaciones(Integer id){estacionesRepository.deleteById(id);}
}
