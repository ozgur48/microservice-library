package com.turkcell.fineservice.application.mapper;

import com.turkcell.fineservice.application.dto.CreatedFineResponse;
import com.turkcell.fineservice.application.dto.FineStatusDto;
import com.turkcell.fineservice.application.dto.FineTypeDto;
import com.turkcell.fineservice.domain.model.Fine;
import org.springframework.stereotype.Component;

@Component
public class CreateFineMapper {
    public CreatedFineResponse toResponse(Fine fine){
        FineStatusDto fineStatusDto = FineStatusDto.valueOf(fine.fineStatus().name());
        FineTypeDto fineTypeDto = FineTypeDto.valueOf(fine.fineType().name());
        return new CreatedFineResponse(
                fine.fineId().value(),
                fine.loanId().value(),
                fine.memberId().value(),
                fine.staffId().value(),
                fine.amount().value(),
                fine.reason().value(),
                fineStatusDto,
                fineTypeDto,
                fine.appliedAt().value()
        );
    }
}
