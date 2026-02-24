package com.Metro.org.Service;


import com.Metro.org.Entity.Tren;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrenService {
    List<Tren> getAllTren();
    Tren getTrenById(Integer id);
    Tren saveTren (Tren tren) throws RuntimeException;
    void deleteTren(Integer id);

    Tren updateTren(Integer id, Tren tren);
}
