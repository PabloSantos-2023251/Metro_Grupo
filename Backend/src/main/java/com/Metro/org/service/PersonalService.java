package com.Metro.org.service;

import com.Metro.org.entity.Personal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PersonalService {
    List<Personal> getAllPersonal();
    Personal getPersonalById(Integer id);
    Personal savePersonal(Personal personal);
    Personal updatePersonal(Integer id, Personal personal);
    void deletePersonal(Integer id);
    Optional<Personal> findByEmail(String email);
}