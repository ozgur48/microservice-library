package com.turkcell.loanservice.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreatedLoanResponse(UUID loanId,
                                  UUID memberId,
                                  UUID bookId,
                                  UUID staffId,
                                  LocalDateTime dueDate,
                                  LocalDateTime loanDate,
                                  LocalDateTime returnDate,
                                  LoanStatusDto loanStatusDto) {
}
