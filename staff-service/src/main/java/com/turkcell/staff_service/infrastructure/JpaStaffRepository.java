package com.turkcell.staff_service.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface JpaStaffRepository extends JpaRepository<JpaStaffEntity, UUID> {
}
