package com.Metro.org.service;


import com.Metro.org.entity.Trenes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrenService {
    List<Trenes> getAllTren();
    Trenes getTrenById(Integer id);
    Trenes saveTren (Trenes trenes) throws RuntimeException;
    void deleteTren(Integer id);

    Trenes updateTren(Integer id, Trenes trenes);
}
