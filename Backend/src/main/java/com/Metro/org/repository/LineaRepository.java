package com.DavidQuintanilla.metroSubterraneo.repository;

import com.DavidQuintanilla.metroSubterraneo.entity.Linea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineaRepository extends JpaRepository<Linea,Integer> {
}
