package com.Metro.org.service;

import com.Metro.org.entity.TicketSoporte;
import com.Metro.org.entity.TicketSoporte.EstadoTicket;
import java.util.List;
import java.util.Optional;

public interface TicketService {
    TicketSoporte save(TicketSoporte ticket);
    List<TicketSoporte> getAll();
    Optional<TicketSoporte> buscarPorId(Integer id);
    void cambiarEstado(Integer id, EstadoTicket nuevoEstado);
    void delete(Integer id);
}