package com.Metro.org.service;

import com.Metro.org.entity.Mantenimiento;
import com.Metro.org.repository.MantenimientoRepository;
import com.Metro.org.repository.TrenRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class MantenimientoServiceImplements implements MantenimientoService {

    private final MantenimientoRepository mantenimientoRepository;
    private final TrenRepository trenRepository;

    public MantenimientoServiceImplements(MantenimientoRepository mantenimientoRepository, TrenRepository trenRepository) {
        this.mantenimientoRepository = mantenimientoRepository;
        this.trenRepository = trenRepository;
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
        validarMantenimiento(mantenimiento);
        return mantenimientoRepository.save(mantenimiento);
    }

    @Override
    public Mantenimiento updateMantenimiento(Integer id, Mantenimiento mantenimiento) {
        validarMantenimiento(mantenimiento);
        return mantenimientoRepository.findById(id).map(existente -> {
            existente.setIdTren(mantenimiento.getIdTren());
            existente.setFecha(mantenimiento.getFecha());
            existente.setDescripcion(mantenimiento.getDescripcion());
            return mantenimientoRepository.save(existente);
        }).orElse(null);
    }

    private void validarMantenimiento(Mantenimiento mantenimiento) {
        if (!trenRepository.existsById(mantenimiento.getIdTren())) {
            throw new RuntimeException("El tren con ID " + mantenimiento.getIdTren() + " no existe.");
        }
        if (mantenimiento.getFecha().isBefore(LocalDate.now())) {
            throw new RuntimeException("No se permiten registros en fechas pasadas.");
        }
        if (mantenimiento.getDescripcion().trim().length() < 10) {
            throw new RuntimeException("La descripción debe tener al menos 10 caracteres.");
        }
    }

    @Override
    public void deleteMantenimiento(Integer id) {
        mantenimientoRepository.deleteById(id);
    }

    @Override
    public List<Mantenimiento> getMantenimientosByTren(Integer idTren) {
        return mantenimientoRepository.findByIdTren(idTren);
    }
}