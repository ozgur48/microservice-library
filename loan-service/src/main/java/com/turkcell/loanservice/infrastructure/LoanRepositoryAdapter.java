package com.turkcell.loanservice.infrastructure;

import com.turkcell.loanservice.domain.model.Loan;
import com.turkcell.loanservice.domain.model.LoanId;
import com.turkcell.loanservice.domain.port.LoanRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LoanRepositoryAdapter implements LoanRepository {
    private final JpaLoanRepository jpaLoanRepository;
    private final LoanEntityMapper loanEntityMapper;

    public LoanRepositoryAdapter(JpaLoanRepository jpaLoanRepository, LoanEntityMapper loanEntityMapper) {
        this.jpaLoanRepository = jpaLoanRepository;
        this.loanEntityMapper = loanEntityMapper;
    }

    @Override
    public Loan save(Loan loan) {
        JpaLoanEntity entity = loanEntityMapper.toEntity(loan);
        entity = jpaLoanRepository.save(entity);
        return loanEntityMapper.toDomain(entity);
    }

    @Override
    public Optional<Loan> findById(LoanId loanId) {
        return jpaLoanRepository.findById(loanId.value()).map(loanEntityMapper::toDomain);
    }

    @Override
    public List<Loan> findAll() {
        return jpaLoanRepository.findAll()
                .stream()
                .map(loanEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Loan> findAllPaged(Integer pageIndex, Integer pageSize) {
        // Sayfalama isteğini oluştur
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);

        // Repository'den Page<JpaLoanEntity> al
        Page<JpaLoanEntity> entityPage = jpaLoanRepository.findAll(pageRequest);
        return entityPage.stream().map(loanEntityMapper::toDomain).toList();
    }

    @Override
    public void delete(LoanId loanId) {
        jpaLoanRepository.deleteById(loanId.value());
    }
}
