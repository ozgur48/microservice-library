package com.turkcell.fineservice.domain.port;


import com.turkcell.fineservice.domain.model.Fine;
import com.turkcell.fineservice.domain.model.FineId;

import java.util.List;
import java.util.Optional;

public interface FineRepository {
    Fine save(Fine fine);
    Optional<Fine> findById(FineId fineId);
    List<Fine> findAll();
    List<Fine> findAllPaged(Integer pageIndex, Integer pageSize);
    void delete(FineId fineId);
}