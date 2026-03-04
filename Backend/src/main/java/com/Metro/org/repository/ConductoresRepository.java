package com.Metro.org.repository;

import com.Metro.org.entity.Conductores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConductoresRepository extends JpaRepository<Conductores,Integer> {
}



