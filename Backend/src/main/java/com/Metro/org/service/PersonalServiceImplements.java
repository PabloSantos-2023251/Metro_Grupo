package com.Metro.org.service;

import com.PabloSantos.org.entity.Personal;
import com.PabloSantos.org.repository.PersonalRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonalServiceImplements implements PersonalService {
    private final PersonalRepository repository;

    public PersonalServiceImplements(PersonalRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Personal> getAllPersonal() {
        return repository.findAll();
    }

    @Override
    public Personal getPersonalById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Personal savePersonal(Personal personal) {
        return repository.save(personal);
    }

    @Override
    public Personal updatePersonal(Integer id, Personal personal) {
        personal.setId_personal(id);
        return repository.save(personal);
    }

    @Override
    public void deletePersonal(Integer id) {
        repository.deleteById(id);
    }
}