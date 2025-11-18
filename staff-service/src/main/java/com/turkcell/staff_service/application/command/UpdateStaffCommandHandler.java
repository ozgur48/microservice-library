package com.turkcell.staff_service.application.command;

import com.turkcell.staff_service.application.dto.UpdatedStaffResponse;
import com.turkcell.staff_service.application.mapper.UpdateStaffMapper;
import com.turkcell.staff_service.cqrs.CommandHandler;
import com.turkcell.staff_service.domain.exception.StaffNotFoundException;
import com.turkcell.staff_service.domain.model.*;
import com.turkcell.staff_service.domain.repository.StaffRepository;

public class UpdateStaffCommandHandler implements CommandHandler<UpdateStaffCommand, UpdatedStaffResponse> {

    private final StaffRepository staffRepository;
    private final UpdateStaffMapper updateStaffMapper;

    public UpdateStaffCommandHandler(StaffRepository staffRepository, UpdateStaffMapper updateStaffMapper) {
        this.staffRepository = staffRepository;
        this.updateStaffMapper = updateStaffMapper;
    }

    @Override
    public UpdatedStaffResponse handle(UpdateStaffCommand command) {
        Staff staff = staffRepository.findById(new StaffId(command.id()))
                .orElseThrow(()-> new StaffNotFoundException(command.id()));
        Name name = new Name(command.name());
        Email email = new Email(command.email());
        Phone phone = new Phone(command.phone());

        staff.updateName(name);
        staff.updateEmail(email);
        staff.updatePhone(phone);

        return updateStaffMapper.toResponse(staff);
    }
}
