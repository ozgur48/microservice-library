package com.turkcell.fineservice.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaFineRepository extends JpaRepository<JpaFineEntity, UUID> {
}