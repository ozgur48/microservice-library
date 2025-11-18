package com.turkcell.staff_service.domain.repository;

import com.turkcell.staff_service.domain.model.Staff;
import com.turkcell.staff_service.domain.model.StaffId;

import java.util.List;
import java.util.Optional;

public interface StaffInterface {
    Staff save(Staff staff);
    Optional<Staff> findById(StaffId staffId);
    List<Staff> findAll();
    List<Staff> findAllPaged (Integer pageIndex, Integer pageSize);
    void delete(StaffId staffId);
}
