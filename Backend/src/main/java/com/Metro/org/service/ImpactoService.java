package com.Metro.org.service;

import com.PabloSantos.org.entity.ImpactoTrafico;
import java.util.List;

public interface ImpactoService {
    List<ImpactoTrafico> getAllImpactos();
    ImpactoTrafico getImpactoById(Integer id);
    ImpactoTrafico saveImpacto(ImpactoTrafico impacto) throws RuntimeException;
    ImpactoTrafico updateImpacto(Integer id, ImpactoTrafico impacto);
    void deleteImpacto(Integer id);
}