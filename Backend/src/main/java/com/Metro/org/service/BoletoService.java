package com.Metro.org.service;

import com.Metro.org.entity.Boleto;
import java.util.List;

public interface BoletoService {
    List<Boleto> getAllBoletos();
    Boleto getBoletoById(Integer id);
    Boleto saveBoleto(Boleto boleto);
    Boleto updateBoleto(Integer id, Boleto boleto);
    void deleteBoleto(Integer id);
    List<Boleto> getBoletosByPasajero(Integer idPasajero);
}