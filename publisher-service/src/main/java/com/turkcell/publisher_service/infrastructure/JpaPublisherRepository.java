package com.turkcell.publisher_service.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaPublisherRepository extends JpaRepository<JpaPublisherEntity, UUID> {
}
