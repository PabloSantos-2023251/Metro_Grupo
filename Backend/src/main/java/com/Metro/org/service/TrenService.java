package com.Metro.org.service;


import com.Metro.org.entity.trenes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrenService {
    List<trenes> getAllTren();
    trenes getTrenById(Integer id);
    trenes saveTren (trenes trenes) throws RuntimeException;
    void deleteTren(Integer id);

    trenes updateTren(Integer id, trenes trenes);
}
