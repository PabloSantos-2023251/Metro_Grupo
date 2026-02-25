package com.Metro.org.service;

import com.PabloSantos.org.entity.ImpactoTrafico;
import com.PabloSantos.org.repository.ImpactoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ImpactoServiceImplements implements ImpactoService {
    private final ImpactoRepository impactoRepository;

    public ImpactoServiceImplements(ImpactoRepository impactoRepository) {
        this.impactoRepository = impactoRepository;
    }

    @Override
    public List<ImpactoTrafico> getAllImpactos() {
        return impactoRepository.findAll();
    }

    @Override
    public ImpactoTrafico getImpactoById(Integer id) {
        return impactoRepository.findById(id).orElse(null);
    }

    @Override
    public ImpactoTrafico saveImpacto(ImpactoTrafico impacto) throws RuntimeException {
        return impactoRepository.save(impacto);
    }

    @Override
    public ImpactoTrafico updateImpacto(Integer id, ImpactoTrafico impacto) {
        impacto.setId_impacto(id);
        return impactoRepository.save(impacto);
    }

    @Override
    public void deleteImpacto(Integer id) {
        impactoRepository.deleteById(id);
    }
}