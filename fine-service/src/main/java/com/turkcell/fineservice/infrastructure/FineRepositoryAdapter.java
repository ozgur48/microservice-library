package com.turkcell.fineservice.infrastructure;

import com.turkcell.fineservice.domain.model.Fine;
import com.turkcell.fineservice.domain.model.FineId;
import com.turkcell.fineservice.domain.port.FineRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class FineRepositoryAdapter implements FineRepository {
    private final JpaFineRepository jpaFineRepository;
    private final FineEntityMapper entityMapper;

    public FineRepositoryAdapter(JpaFineRepository jpaFineRepository, FineEntityMapper entityMapper) {
        this.jpaFineRepository = jpaFineRepository;
        this.entityMapper = entityMapper;
    }



    @Override
    public Fine save(Fine fine) {
        JpaFineEntity entity = entityMapper.toEntity(fine);
        entity = jpaFineRepository.save(entity);
        return entityMapper.toDomain(entity);
    }

    @Override
    public Optional<Fine> findById(FineId fineId) {
        return jpaFineRepository.findById(fineId.value()).map(entityMapper::toDomain);
    }

    @Override
    public List<Fine> findAll() {
        return jpaFineRepository.findAll()
                .stream()
                .map(entityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Fine> findAllPaged(Integer pageIndex, Integer pageSize) {
        // Sayfalama isteğini oluştur
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);

        Page<JpaFineEntity> entityPage = jpaFineRepository.findAll(pageRequest);
        return entityPage.stream().map(entityMapper::toDomain).toList();
    }

    @Override
    public void delete(FineId fineId) {
        jpaFineRepository.deleteById(fineId.value());
    }
}
