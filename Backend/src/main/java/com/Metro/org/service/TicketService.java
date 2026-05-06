package com.Metro.org.service;

import com.Metro.org.entity.TicketSoporte;
import java.util.List;

public interface TicketService {
    List<TicketSoporte> getAll();
    void save(TicketSoporte ticket);
    void delete(Integer id);
}