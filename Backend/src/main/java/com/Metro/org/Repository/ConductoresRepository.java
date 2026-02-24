package com.Metro.org.Repository;

import com.Metro.org.Entity.Conductores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConductoresRepository extends JpaRepository<Conductores,Integer> {
}



