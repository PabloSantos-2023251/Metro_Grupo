package com.Metro.org.service;

import com.Metro.org.entity.Personal;
import com.Metro.org.repository.PersonalRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Personal> findByEmail(String email) {
        return repository.findByEmail(email.trim());
    }
}