package com.Metro.org.repository;

import com.PabloSantos.org.entity.ImpactoTrafico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpactoRepository extends JpaRepository<ImpactoTrafico, Integer> {
}