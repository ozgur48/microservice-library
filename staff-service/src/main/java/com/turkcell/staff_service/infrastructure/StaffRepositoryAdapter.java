package com.turkcell.staff_service.infrastructure;

import com.turkcell.staff_service.domain.model.Staff;
import com.turkcell.staff_service.domain.model.StaffId;
import com.turkcell.staff_service.domain.repository.StaffRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StaffRepositoryAdapter implements StaffRepository {

    private final JpaStaffRepository jpaStaffRepository;
    private StaffEntityMapper staffEntityMapper;

    public StaffRepositoryAdapter(JpaStaffRepository jpaStaffRepository, StaffEntityMapper staffEntityMapper) {
        this.jpaStaffRepository = jpaStaffRepository;
        this.staffEntityMapper = staffEntityMapper;
    }


    @Override
    public Staff save(Staff staff) {
        JpaStaffEntity entity = staffEntityMapper.toEntity(staff);
        entity = jpaStaffRepository.save(entity);
        return staffEntityMapper.toDomain(entity);
    }

    @Override
    public Optional<Staff> findById(StaffId staffId) {
        return jpaStaffRepository.findById(staffId.value()).map(staffEntityMapper::toDomain);
    }

    @Override
    public List<Staff> findAll() {
        return jpaStaffRepository.findAll()
                .stream()
                .map(staffEntityMapper::toDomain).toList();
    }

    @Override
    public List<Staff> findAllPaged(Integer pageIndex, Integer pageSize) {
        return jpaStaffRepository.findAll(PageRequest.of(pageIndex, pageSize))
                .stream()
                .map(staffEntityMapper::toDomain)
                .toList();
    }

    @Override
    public void delete(StaffId staffId) {
        jpaStaffRepository.deleteById(staffId.value());
    }
}
