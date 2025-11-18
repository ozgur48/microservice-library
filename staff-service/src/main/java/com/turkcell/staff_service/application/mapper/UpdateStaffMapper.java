package com.turkcell.staff_service.application.mapper;


import com.turkcell.staff_service.application.dto.UpdatedStaffResponse;
import com.turkcell.staff_service.domain.model.Staff;
import org.springframework.stereotype.Component;

@Component
public class UpdateStaffMapper {
    public UpdatedStaffResponse toResponse(Staff staff){
        return new UpdatedStaffResponse(
                staff.name().value(),
                staff.phone().value(),
                staff.email().value()
        );

    }
}
