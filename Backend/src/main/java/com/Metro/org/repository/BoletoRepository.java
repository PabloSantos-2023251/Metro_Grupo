package com.Metro.org.repository;

import java.util.List;
import com.Metro.org.entity.Boleto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletoRepository extends JpaRepository<Boleto, Integer> {
    List<Boleto> findByPasajero_IdPasajero(Integer idPasajero);
}

