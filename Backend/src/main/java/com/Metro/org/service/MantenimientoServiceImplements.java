package com.Metro.org.service;

import com.Metro.org.entity.Mantenimiento;
import com.Metro.org.repository.MantenimientoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MantenimientoServiceImplements implements MantenimientoService {

    private final MantenimientoRepository mantenimientoRepository;

    public MantenimientoServiceImplements(MantenimientoRepository mantenimientoRepository) {
        this.mantenimientoRepository = mantenimientoRepository;
    }

    @Override
    public List<Mantenimiento> getAllMantenimientos() {
        return mantenimientoRepository.findAll();
    }

    @Override
    public Mantenimiento getMantenimientoById(Integer id) {
        return mantenimientoRepository.findById(id).orElse(null);
    }

    @Override
    public Mantenimiento saveMantenimiento(Mantenimiento mantenimiento) {
        return mantenimientoRepository.save(mantenimiento);
    }

    @Override
    public Mantenimiento updateMantenimiento(Integer id, Mantenimiento mantenimiento) {
        return mantenimientoRepository.findById(id).map(existente -> {
            existente.setFecha(mantenimiento.getFecha());
            existente.setDescripcion(mantenimiento.getDescripcion());
            existente.setIdTren(mantenimiento.getIdTren());
            return mantenimientoRepository.save(existente);
        }).orElse(null);
    }

    @Override
    public boolean deleteMantenimiento(Integer id) {
        if (mantenimientoRepository.existsById(id)) {
            mantenimientoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}