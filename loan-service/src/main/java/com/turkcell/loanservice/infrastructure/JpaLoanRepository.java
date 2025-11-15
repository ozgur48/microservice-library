package com.turkcell.loanservice.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaLoanRepository extends JpaRepository<JpaLoanEntity, UUID> {
}
