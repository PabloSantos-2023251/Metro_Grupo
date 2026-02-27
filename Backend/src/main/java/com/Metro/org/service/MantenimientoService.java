package com.Metro.org.Service;

import com.Metro.org.Entity.Mantenimiento;
import java.util.List;

public interface MantenimientoService {
    List<Mantenimiento> getAllMantenimientos();
    Mantenimiento getMantenimientoById(Integer id);
    Mantenimiento saveMantenimiento(Mantenimiento mantenimiento);
    Mantenimiento updateMantenimiento(Integer id, Mantenimiento mantenimiento);
    boolean deleteMantenimiento(Integer id); // Cambiado a boolean
}