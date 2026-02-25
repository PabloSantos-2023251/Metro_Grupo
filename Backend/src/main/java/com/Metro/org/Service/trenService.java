package com.Metro.org.Service;


import com.Metro.org.Entity.tren;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface trenService {
    List<tren> getAllTren();
    tren getTrenById(Integer id);
    tren saveTren (tren tren) throws RuntimeException;
    void deleteTren(Integer id);

    tren updateTren(Integer id, tren tren);
}
