package com.turkcell.author_service.infrastracture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaAuthorRepository extends JpaRepository<JpaAuthorEntity, UUID> {

}
