package com.turkcell.staff_service.application.query;

import com.turkcell.staff_service.application.dto.StaffDetails;
import com.turkcell.staff_service.application.mapper.StaffDetailsMapper;
import com.turkcell.staff_service.cqrs.QueryHandler;
import com.turkcell.staff_service.domain.exception.StaffNotFoundException;
import com.turkcell.staff_service.domain.model.Staff;
import com.turkcell.staff_service.domain.model.StaffId;
import com.turkcell.staff_service.domain.repository.StaffRepository;

public class GetStaffDetailsQueryHandler implements QueryHandler<GetStaffDetailsQuery, StaffDetails> {

    private final StaffRepository staffRepository;
    private final StaffDetailsMapper staffDetailsMapper;

    public GetStaffDetailsQueryHandler(StaffRepository staffRepository, StaffDetailsMapper staffDetailsMapper) {
        this.staffRepository = staffRepository;
        this.staffDetailsMapper = staffDetailsMapper;
    }

    @Override
    public StaffDetails handle(GetStaffDetailsQuery query) {
        StaffId staffId = new StaffId(query.id());
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(()-> new StaffNotFoundException(query.id()));
        return staffDetailsMapper.toResponse(staff);
    }
}
