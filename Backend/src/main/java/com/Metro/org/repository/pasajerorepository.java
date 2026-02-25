package com.Metro.org.repository;

import com.Metro.org.entity.pasajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface pasajerorepository extends JpaRepository<pasajero, Integer> {

}
