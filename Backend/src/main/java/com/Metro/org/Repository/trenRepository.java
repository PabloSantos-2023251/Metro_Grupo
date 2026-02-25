package com.Metro.org.Repository;

import com.Metro.org.Entity.tren;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface trenRepository extends JpaRepository<tren,Integer> {
}



