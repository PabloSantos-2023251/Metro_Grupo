package com.Metro.org.repository;

import com.Metro.org.entity.Tren;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrenRepository extends JpaRepository<Tren,Integer> {
}



