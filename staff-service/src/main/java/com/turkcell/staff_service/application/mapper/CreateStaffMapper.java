package com.turkcell.staff_service.application.mapper;

import com.turkcell.staff_service.application.command.CreateStaffCommand;
import com.turkcell.staff_service.application.dto.CreatedStaffResponse;
import com.turkcell.staff_service.domain.model.*;
import org.springframework.stereotype.Component;

@Component
public class CreateStaffMapper {

    public Staff toDomain(CreateStaffCommand command){

        StaffLevel staffLevel = StaffLevel.valueOf(command.staffLevel().name());

        return Staff.create(
                new Name(command.name()),
                staffLevel,
                new Email(command.email().value()),
                new Phone(command.phone().value())
        );
    }


    public CreatedStaffResponse toResponse(Staff staff){
        return new CreatedStaffResponse(
                staff.id().value(),
                staff.name().value()
        );
    }
}
