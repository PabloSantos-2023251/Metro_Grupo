package com.Metro.org.service;

import com.Metro.org.entity.Linea;
import com.Metro.org.repository.LineaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineaServiceImplement implements LineaService {
    private final LineaRepository lineaRepository;

    public LineaServiceImplement(LineaRepository lineaRepository){
        this.lineaRepository = lineaRepository;
    }

    @Override
    public List<Linea> getAllLinea(){
        return lineaRepository.findAll();
    }

    @Override
    public Linea getLineaById(Integer id){
        return lineaRepository.findById(id).orElse(null);
    }

    @Override
    public Linea saveLinea(Linea linea) throws RuntimeException{
        return lineaRepository.save(linea);
    }

    @Override
    public Linea updateLinea (Integer id, Linea lineaDetalles){
        Linea lineaExistente = lineaRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Linea no encotrada con el ID:"+id));

        lineaExistente.setNombreLinea(lineaDetalles.getNombreLinea());
        lineaExistente.setColor(lineaDetalles.getColor());
        lineaExistente.setLogitudKm(lineaDetalles.getLogitudKm());

        return lineaRepository.save(lineaExistente);
    }

    @Override
    public void deleteLinea(Integer id){
        lineaRepository.deleteById(id);
    }


}
