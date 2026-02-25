package com.Metro.org.Service;


import com.Metro.org.Entity.tren;
import com.Metro.org.Repository.trenRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class trenServiceImplements implements trenService {
    private final trenRepository TrenRepository;


    public trenServiceImplements(trenRepository trenRepository) {
        TrenRepository = trenRepository;
    }

    @Override
    public List<tren> getAllTren() {
        return TrenRepository.findAll();
    }

    @Override
    public tren getTrenById(Integer id) {
        return TrenRepository.findById(id).orElse(null);
    }

    @Override
    public tren saveTren(tren tren) throws RuntimeException {
        return TrenRepository.save(tren);
    }



    @Override
    public tren updateTren(Integer id, tren tren) {
        tren existingTren = TrenRepository.findById(id).orElseThrow(() -> new RuntimeException("El Tren no existe"));

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