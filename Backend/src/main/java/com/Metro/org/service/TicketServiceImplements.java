package com.Metro.org.service;

import com.Metro.org.entity.TicketSoporte;
import com.Metro.org.entity.TicketSoporte.EstadoTicket;
import com.Metro.org.repository.TicketRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImplements implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImplements(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public TicketSoporte save(TicketSoporte ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public List<TicketSoporte> getAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Optional<TicketSoporte> buscarPorId(Integer id) {
        return ticketRepository.findById(id);
    }

    @Override
    public void cambiarEstado(Integer id, EstadoTicket nuevoEstado) {
        ticketRepository.findById(id).ifPresent(ticket -> {
            ticket.setEstado(nuevoEstado);
            ticketRepository.save(ticket);
        });
    }

    @Override
    public void delete(Integer id) {
        ticketRepository.deleteById(id);
    }
}