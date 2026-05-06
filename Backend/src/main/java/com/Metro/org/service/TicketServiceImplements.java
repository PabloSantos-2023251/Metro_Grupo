package com.Metro.org.service;

import com.Metro.org.entity.TicketSoporte;
import com.Metro.org.repository.TicketRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketServiceImplements implements TicketService {
    private final TicketRepository repository;

    public TicketServiceImplements(TicketRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TicketSoporte> getAll() {
        return repository.findAll();
    }

    @Override
    public void save(TicketSoporte ticket) {
        repository.save(ticket);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}