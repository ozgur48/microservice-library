package com.turkcell.staff_service.infrastructure;

import com.turkcell.staff_service.domain.model.*;
import org.springframework.stereotype.Component;

@Component
public class StaffEntityMapper {
    public JpaStaffEntity toEntity(Staff staff){
        return new JpaStaffEntity(
                staff.id().value(),
                staff.name().value(),
                staff.email().value(),
                staff.phone().value(),
                staff.staffLevel()
        );
    }

    public Staff toDomain(JpaStaffEntity entity){
        return Staff.rehydrate(
                new StaffId(entity.id()),
                new Name(entity.name()),
                entity.staffLevel(),
                new Email(entity.email()),
                new Phone(entity.phone())
        );
    }
}
