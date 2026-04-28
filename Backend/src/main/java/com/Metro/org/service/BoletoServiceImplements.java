package com.Metro.org.service;

import com.Metro.org.entity.Boleto;
import com.Metro.org.repository.BoletoRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
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
        validarBoleto(boleto);
        return boletoRepository.save(boleto);
    }

    @Override
    public Boleto updateBoleto(Integer id, Boleto boleto) {
        validarBoleto(boleto);
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

    @Override
    public List<Boleto> getBoletosByPasajero(Integer idPasajero) {
        return boletoRepository.findByPasajero_IdPasajero(idPasajero);
    }

    private void validarBoleto(Boleto boleto) {
        //La fecha no puede ser anterior a el dia de hoy//
        if (boleto.getFecha() != null && boleto.getFecha().isBefore(LocalDate.now())) {
            throw new RuntimeException("La fecha del boleto no puede ser anterior al día de hoy.");
        }

        String tipo = boleto.getPasajero() != null
                ? boleto.getPasajero().getTipoPasajero().trim().toLowerCase()
                : "";

        BigDecimal precio = boleto.getPrecio();

        //Adulto mayor / discapacitado el precio debe ser 0.00//
        if (tipo.equals("adulto mayor") || tipo.equals("discapacitado")) {
            if (precio == null || precio.compareTo(BigDecimal.ZERO) != 0) {
                throw new RuntimeException(
                        "El precio para pasajeros de tipo '" + boleto.getPasajero().getTipoPasajero()
                                + "' debe ser 0.00.");
            }
        }

        //Estudiante el precio debe ser menor al precio general//
        if (tipo.equals("estudiante")) {
            BigDecimal precioGeneral = new BigDecimal("2.00");
            if (precio == null || precio.compareTo(precioGeneral) >= 0) {
                throw new RuntimeException(
                        "El precio para pasajeros de tipo 'estudiante' debe ser menor al precio general (Q"
                                + precioGeneral + ").");
            }
        }
    }
}
