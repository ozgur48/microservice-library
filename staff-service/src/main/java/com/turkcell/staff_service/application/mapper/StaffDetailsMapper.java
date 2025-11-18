package com.turkcell.staff_service.application.mapper;

import com.turkcell.staff_service.application.dto.StaffDetails;
import com.turkcell.staff_service.domain.model.Staff;

public class StaffDetailsMapper {

    public StaffDetails toResponse(Staff staff){
        return  new StaffDetails(
                staff.name().value(),
                staff.email().value(),
                staff.phone().value()
        );
    }
}
