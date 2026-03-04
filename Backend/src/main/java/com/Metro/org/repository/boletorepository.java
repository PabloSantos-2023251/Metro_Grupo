package com.Metro.org.repository;

import com.Metro.org.entity.boleto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface boletorepository extends JpaRepository<boleto, Integer> {

}

