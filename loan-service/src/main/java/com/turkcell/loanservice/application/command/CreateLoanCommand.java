package com.turkcell.loanservice.application.command;

import com.turkcell.loanservice.application.dto.CreatedLoanResponse;
import com.turkcell.loanservice.application.dto.LoanStatusDto;
import com.turkcell.loanservice.cqrs.Command;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateLoanCommand (@NotNull UUID memberId, @NotNull UUID bookId,
                                 @NotNull UUID staffId, LocalDateTime dueDate,
                                 LocalDateTime loanDate, LocalDateTime returnDate,
                                 @NotNull LoanStatusDto loanStatus) implements Command<CreatedLoanResponse> {
}
