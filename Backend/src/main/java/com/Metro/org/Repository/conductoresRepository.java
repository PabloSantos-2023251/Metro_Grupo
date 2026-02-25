package com.Metro.org.Repository;

import com.Metro.org.Entity.conductores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface conductoresRepository extends JpaRepository<conductores,Integer> {
}



