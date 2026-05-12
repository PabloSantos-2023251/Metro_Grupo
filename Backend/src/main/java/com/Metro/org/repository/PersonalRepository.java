package com.Metro.org.repository;

import com.Metro.org.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PersonalRepository extends JpaRepository<Personal, Integer> {
    Optional<Personal> findByEmail(String email);
}