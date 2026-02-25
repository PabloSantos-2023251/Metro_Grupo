package com.Metro.org.repository;

import com.Metro.org.entity.trenes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrenRepository extends JpaRepository<trenes,Integer> {
}



