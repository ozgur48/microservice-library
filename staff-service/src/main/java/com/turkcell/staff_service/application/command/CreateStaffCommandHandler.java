package com.turkcell.staff_service.application.command;

import com.turkcell.staff_service.application.dto.CreatedStaffResponse;
import com.turkcell.staff_service.application.mapper.CreateStaffMapper;
import com.turkcell.staff_service.cqrs.CommandHandler;
import com.turkcell.staff_service.domain.model.Staff;
import com.turkcell.staff_service.domain.repository.StaffRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateStaffCommandHandler implements CommandHandler<CreateStaffCommand, CreatedStaffResponse> {

    private final StaffRepository staffRepository;
    private final CreateStaffMapper createStaffMapper;

    public CreateStaffCommandHandler(StaffRepository staffRepository, CreateStaffMapper createStaffMapper) {
        this.staffRepository = staffRepository;
        this.createStaffMapper = createStaffMapper;
    }

    @Override
    public CreatedStaffResponse handle(CreateStaffCommand command) {
        Staff staff = createStaffMapper.toDomain(command);
        staff = staffRepository.save(staff);
        return createStaffMapper.toResponse(staff);
    }
}
