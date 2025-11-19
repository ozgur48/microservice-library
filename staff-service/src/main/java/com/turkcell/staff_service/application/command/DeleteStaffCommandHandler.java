package com.turkcell.staff_service.application.command;

import com.turkcell.staff_service.application.dto.DeletedStaffResponse;
import com.turkcell.staff_service.cqrs.CommandHandler;
import com.turkcell.staff_service.domain.exception.StaffNotFoundException;
import com.turkcell.staff_service.domain.model.StaffId;
import com.turkcell.staff_service.domain.repository.StaffRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteStaffCommandHandler implements CommandHandler<DeleteStaffCommand, DeletedStaffResponse> {
    private final StaffRepository staffRepository;

    public DeleteStaffCommandHandler(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public DeletedStaffResponse handle(DeleteStaffCommand command) {
        StaffId staffId = new StaffId(command.id());
        staffRepository.findById(staffId).orElseThrow(()-> new StaffNotFoundException(command.id()));

        staffRepository.delete(staffId);
        return new DeletedStaffResponse(command.id());
    }
}
