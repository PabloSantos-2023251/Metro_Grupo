package com.Metro.org.Repository;

import com.Metro.org.Entity.Tren;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrenRepository extends JpaRepository<Tren,Integer> {
}



