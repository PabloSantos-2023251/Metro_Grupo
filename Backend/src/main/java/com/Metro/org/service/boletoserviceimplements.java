package com.Metro.org.service;

import com.Metro.org.entity.boleto;
import com.Metro.org.repository.boletorepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class boletoserviceimplements implements boletoservice {
    private final boletorepository boletoRepository;

    public boletoserviceimplements(boletorepository boletoRepository) {
        this.boletoRepository = boletoRepository;
    }


    @Override
    public List<boleto> getAllBoletos() {
        return boletoRepository.findAll();
    }

    @Override
    public boleto getBoletoById(Integer id) {
        return boletoRepository.findById(id).orElse(null);
    }

    @Override
    public boleto saveBoleto(boleto boleto) throws RuntimeException {
        return boletoRepository.save(boleto);
    }

    @Override
    public boleto updateBoleto(Integer id, boleto boleto) {
        Optional<boleto> boletoExistente = boletoRepository.findById(id);
        if (boletoExistente.isPresent()) {
            boleto newBoleto = boletoExistente.get();
            newBoleto.setFecha(boleto.getFecha());
            newBoleto.setPrecio(boleto.getPrecio());
            newBoleto.setPasajero(boleto.getPasajero());
            return boletoRepository.save(newBoleto);
        } else {
            return null;
        }
    }

    @Override
    public void deleteBoleto(Integer id){ boletoRepository.deleteById(id);

    }
}
