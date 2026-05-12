package com.Metro.org.service;

import com.Metro.org.entity.Trenes;
import com.Metro.org.repository.TrenRepository;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrenServiceImplements implements TrenService {

    private final TrenRepository TrenRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public TrenServiceImplements(TrenRepository trenRepository) {
        this.TrenRepository = trenRepository;
    }

    @Override
    public List<Trenes> getAllTren() {
        return TrenRepository.findAll();
    }

    @Override
    public Trenes getTrenById(Integer id) {
        return TrenRepository.findById(id).orElse(null);
    }

    @Override
    public Trenes saveTren(Trenes trenes) throws RuntimeException {
        return TrenRepository.save(trenes);
    }

    @Override
    public Trenes updateTren(Integer id, Trenes trenes) {
        Trenes existingTrenes = TrenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El Tren no existe"));

        existingTrenes.setModelo(trenes.getModelo());
        existingTrenes.setCapacidadPasajeros(trenes.getCapacidadPasajeros());
        existingTrenes.setEstado(trenes.getEstado());

        return TrenRepository.save(existingTrenes);
    }

    @Override
    @Transactional
    public void deleteTren(Integer id) {

        if (!TrenRepository.existsById(id)) {
            throw new RuntimeException("Tren no existe");
        }

        entityManager.createNativeQuery("DELETE FROM horarios WHERE id_tren = :id")
                .setParameter("id", id)
                .executeUpdate();

        TrenRepository.deleteById(id);
    }
}