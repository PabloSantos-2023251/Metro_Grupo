package com.Metro.org.service;


import com.Metro.org.entity.Tren;
import com.Metro.org.repository.TrenRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrenServiceImplements implements TrenService {
    private final TrenRepository TrenRepository;


    public TrenServiceImplements(TrenRepository trenRepository) {
        TrenRepository = trenRepository;
    }

    @Override
    public List<Tren> getAllTren() {
        return TrenRepository.findAll();
    }

    @Override
    public Tren getTrenById(Integer id) {
        return TrenRepository.findById(id).orElse(null);
    }

    @Override
    public Tren saveTren(Tren tren) throws RuntimeException {
        return TrenRepository.save(tren);
    }



    @Override
    public Tren updateTren(Integer id, Tren tren) {
        Tren existingTren = TrenRepository.findById(id).orElseThrow(() -> new RuntimeException("El Tren no existe"));

        existingTren.setModelo(tren.getModelo());
        existingTren.setCapacidadPasajeros(tren.getCapacidadPasajeros());
        existingTren.setEstado(tren.getEstado());

        return TrenRepository.save(existingTren);

    }

    @Override
    public void deleteTren(Integer id) {
        if (!TrenRepository.existsById(id)) {
            throw new RuntimeException("Tren no existe");
        }
        TrenRepository.deleteById(id);
    }
}