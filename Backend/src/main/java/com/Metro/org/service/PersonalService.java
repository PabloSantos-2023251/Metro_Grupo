package com.Metro.org.service;

import com.PabloSantos.org.entity.Personal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonalService {
    List<Personal> getAllPersonal();
    Personal getPersonalById(Integer id);
    Personal savePersonal(Personal personal);
    Personal updatePersonal(Integer id, Personal personal);
    void deletePersonal(Integer id);
}