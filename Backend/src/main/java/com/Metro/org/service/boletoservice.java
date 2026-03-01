package com.Metro.org.service;

import com.Metro.org.entity.boleto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface boletoservice {
    List<boleto> getAllBoletos();
    boleto getBoletoById(Integer id);
    boleto saveBoleto(boleto boleto) throws RuntimeException;
    boleto updateBoleto(Integer id, boleto boleto);
    void deleteBoleto(Integer id);
}