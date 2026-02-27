package com.Metro.org.repository;

import com.Metro.org.entity.Linea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineaRepository extends JpaRepository<Linea,Integer> {
}
