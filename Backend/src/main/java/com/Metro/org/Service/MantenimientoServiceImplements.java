package com.Metro.org.Service;

import com.Metro.org.Entity.Mantenimiento;
import com.Metro.org.Repository.MantenimientoRepository;
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
        Mantenimiento existente = mantenimientoRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setFecha(mantenimiento.getFecha());
            existente.setDescripcion(mantenimiento.getDescripcion());
            existente.setIdTren(mantenimiento.getIdTren());
            return mantenimientoRepository.save(existente);
        }
        return null;
    }

    @Override
    public void deleteMantenimiento(Integer id) {
        mantenimientoRepository.deleteById(id);
    }
}