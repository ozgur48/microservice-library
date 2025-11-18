package com.turkcell.staff_service.interfaces.web;

import com.turkcell.staff_service.application.command.DeleteStaffCommand;
import com.turkcell.staff_service.application.command.UpdateStaffCommand;
import com.turkcell.staff_service.application.dto.DeletedStaffResponse;
import com.turkcell.staff_service.application.dto.StaffDetails;
import com.turkcell.staff_service.application.dto.UpdatedStaffResponse;
import com.turkcell.staff_service.application.query.GetStaffDetailsQuery;
import com.turkcell.staff_service.cqrs.CommandHandler;
import com.turkcell.staff_service.cqrs.QueryHandler;
import jakarta.validation.Valid;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.turkcell.staff_service.application.command.CreateStaffCommand;
import com.turkcell.staff_service.application.dto.CreatedStaffResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/staffs")
@Validated
public class StaffsController {

    private final CommandHandler<CreateStaffCommand, CreatedStaffResponse> createStaffCommandHandler;
    private final CommandHandler<UpdateStaffCommand, UpdatedStaffResponse> updateStaffCommandHandler;
    private final CommandHandler<DeleteStaffCommand, DeletedStaffResponse> deleteStaffCommandHandler;

    private final QueryHandler<GetStaffDetailsQuery, StaffDetails> getStaffDetailsQueryHandler;
    private final StreamBridge streamBridge;

    public StaffsController(CommandHandler<CreateStaffCommand, CreatedStaffResponse> createStaffCommandHandler, CommandHandler<UpdateStaffCommand, UpdatedStaffResponse> updateStaffCommandHandler, CommandHandler<DeleteStaffCommand, DeletedStaffResponse> deleteStaffCommandHandler, QueryHandler<GetStaffDetailsQuery, StaffDetails> getStaffDetailsQueryHandler, StreamBridge streamBridge) {
        this.createStaffCommandHandler = createStaffCommandHandler;
        this.updateStaffCommandHandler = updateStaffCommandHandler;
        this.deleteStaffCommandHandler = deleteStaffCommandHandler;
        this.getStaffDetailsQueryHandler = getStaffDetailsQueryHandler;
        this.streamBridge = streamBridge;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedStaffResponse createdStaff(@Valid @RequestBody CreateStaffCommand command){
        CreatedStaffResponse staff = createStaffCommandHandler.handle(command);
        streamBridge.send("staffCreated-out", staff.name());
        return staff;
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdatedStaffResponse> updateStaff(@Valid @PathVariable UUID id, UpdateStaffCommand command){
        UpdateStaffCommand finalCommand = new UpdateStaffCommand(
                id,
                command.name(),
                command.email(),
                command.phone()
        );
        UpdatedStaffResponse response = updateStaffCommandHandler.handle(finalCommand);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<StaffDetails> getStaff(@Valid @PathVariable UUID id, GetStaffDetailsQuery query){
        GetStaffDetailsQuery finalQuery = new GetStaffDetailsQuery(id);
        StaffDetails response = getStaffDetailsQueryHandler.handle(finalQuery);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DeletedStaffResponse> delete(@Valid @PathVariable UUID id, DeleteStaffCommand command){
        DeleteStaffCommand finalCommand = new DeleteStaffCommand(id);
        DeletedStaffResponse response = deleteStaffCommandHandler.handle(finalCommand);
        return ResponseEntity.ok(response);
    }
}
