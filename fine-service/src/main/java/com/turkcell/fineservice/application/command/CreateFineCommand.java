package com.turkcell.fineservice.application.command;

import com.turkcell.fineservice.application.dto.CreatedFineResponse;
import com.turkcell.fineservice.application.dto.FineTypeDto;
import com.turkcell.fineservice.cqrs.Command;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateFineCommand(
        @NotNull UUID loanId,
        @NotNull UUID memberId,
        @NotNull UUID staffId,
        @Positive BigDecimal amount,
        @NotNull String reason,
        @NotNull FineTypeDto fineTypeDto) implements Command<CreatedFineResponse> { }
