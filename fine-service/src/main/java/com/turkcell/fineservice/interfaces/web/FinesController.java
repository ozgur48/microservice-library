package com.turkcell.fineservice.interfaces.web;

import com.turkcell.fineservice.application.command.CreateFineCommand;
import com.turkcell.fineservice.application.dto.CreatedFineResponse;
import com.turkcell.fineservice.cqrs.CommandHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fines")
@Validated
public class FinesController {
    private final CommandHandler<CreateFineCommand, CreatedFineResponse> createFineCommandHandler;

    public FinesController(CommandHandler<CreateFineCommand, CreatedFineResponse> createFineCommandHandler) {
        this.createFineCommandHandler = createFineCommandHandler;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedFineResponse createFine(@Valid @RequestBody CreateFineCommand command){
        return createFineCommandHandler.handle(command);
    }
}
