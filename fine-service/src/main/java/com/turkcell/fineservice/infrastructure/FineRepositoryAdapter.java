package com.turkcell.fineservice.infrastructure;

import com.turkcell.fineservice.domain.model.Fine;
import com.turkcell.fineservice.domain.model.FineId;
import com.turkcell.fineservice.domain.port.FineRepository;

import java.util.List;
import java.util.Optional;

public class FineRepositoryAdapter implements FineRepository {
    @Override
    public Fine save(Fine fine) {
        return null;
    }

    @Override
    public Optional<Fine> findById(FineId fineId) {
        return Optional.empty();
    }

    @Override
    public List<Fine> findAll() {
        return List.of();
    }

    @Override
    public List<Fine> findAllPaged(Integer pageIndex, Integer pageSize) {
        return List.of();
    }

    @Override
    public void delete(FineId fineId) {

    }
}
