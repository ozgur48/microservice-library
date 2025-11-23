package com.turkcell.fineservice.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreatedFineResponse(
        UUID fineId,
        UUID loanId,
        UUID memberId,
        UUID staffId,
        BigDecimal amount,
        String reason,
        FineStatusDto fineStatusDto,
        FineTypeDto fineTypeDto,
        LocalDateTime appliedAt
) {
}
