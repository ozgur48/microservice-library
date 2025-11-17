package com.turkcell.loanservice.interfaces.web;

import com.turkcell.loanservice.application.command.CreateLoanCommand;
import com.turkcell.loanservice.application.command.CreateLoanCommandHandler;
import com.turkcell.loanservice.application.dto.CreatedLoanResponse;
import com.turkcell.loanservice.cqrs.CommandHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/loans")
@Validated
public class LoansController {
    private final CommandHandler<CreateLoanCommand, CreatedLoanResponse> createLoanCommandHandler;


    public LoansController(CommandHandler<CreateLoanCommand, CreatedLoanResponse> createLoanCommandHandler) {
        this.createLoanCommandHandler = createLoanCommandHandler;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedLoanResponse createLoan(@Valid @RequestBody CreateLoanCommand command){
        return createLoanCommandHandler.handle(command);
    }
}
