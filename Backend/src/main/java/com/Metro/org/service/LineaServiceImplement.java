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
    public Linea saveLinea(Linea linea) {
        String color = linea.getColor().trim().toLowerCase();
        try {
            Linea existente = lineaRepository.findByColorIgnoreCase(color);
            if (existente != null) {
                throw new RuntimeException("El color ya se está utilizando en otra línea");
            }
        } catch (Exception e) {
            throw new RuntimeException("El color ya se está utilizando en otra línea");
        }
        linea.setColor(color);
        return lineaRepository.save(linea);
    }

    @Override
    public Linea updateLinea (Integer id, Linea lineaDetalles){
        Linea lineaExistente = lineaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Linea no encotrada con el ID:"+id));
        String color = lineaDetalles.getColor().trim().toLowerCase();
        try {
            Linea existente = lineaRepository.findByColorIgnoreCase(color);
            if (existente != null && !existente.getIdLinea().equals(id)) {
                throw new RuntimeException("El color ya se está utilizando en otra línea");
            }
        } catch (Exception e){
            throw new RuntimeException("El color ya se está utilizando en otra línea");
        }
        lineaExistente.setNombreLinea(lineaDetalles.getNombreLinea());
        lineaExistente.setColor(color);
        lineaExistente.setLongitudKm(lineaDetalles.getLongitudKm());
        return lineaRepository.save(lineaExistente);
    }

    @Override
    public void deleteLinea(Integer id){
        lineaRepository.deleteById(id);
    }


}
