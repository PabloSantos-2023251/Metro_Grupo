package com.Metro.org.repository;

import com.Metro.org.entity.Mantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MantenimientoRepository extends JpaRepository<Mantenimiento, Integer> {
    List<Mantenimiento> findByIdTren(Integer idTren);
}