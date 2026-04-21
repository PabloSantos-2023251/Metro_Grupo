package com.Metro.org.service;

import com.Metro.org.entity.Boleto;
import com.Metro.org.repository.BoletoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BoletoServiceImplements implements BoletoService {

    private final BoletoRepository boletoRepository;

    public BoletoServiceImplements(BoletoRepository boletoRepository) {
        this.boletoRepository = boletoRepository;
    }

    @Override
    public List<Boleto> getAllBoletos() {
        return boletoRepository.findAll();
    }

    @Override
    public Boleto getBoletoById(Integer id) {
        return boletoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Boleto no encontrado"));
    }

    @Override
    public Boleto saveBoleto(Boleto boleto) {
        return boletoRepository.save(boleto);
    }

    @Override
    public Boleto updateBoleto(Integer id, Boleto boleto) {
        return boletoRepository.findById(id).map(existente -> {
            existente.setFecha(boleto.getFecha());
            existente.setPrecio(boleto.getPrecio());
            existente.setPasajero(boleto.getPasajero());
            return boletoRepository.save(existente);
        }).orElseThrow(() -> new RuntimeException("Boleto no encontrado para actualizar"));
    }

    @Override
    public void deleteBoleto(Integer id) {
        if (!boletoRepository.existsById(id)) {
            throw new RuntimeException("Boleto no encontrado");
        }
        boletoRepository.deleteById(id);
    }
}
