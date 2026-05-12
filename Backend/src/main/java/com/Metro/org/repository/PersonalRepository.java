package com.Metro.org.repository;

import com.Metro.org.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;
=======
>>>>>>> ft-MiguelSantizo2022021
import java.util.Optional;

public interface PersonalRepository extends JpaRepository<Personal, Integer> {
    Optional<Personal> findByEmail(String email);
}