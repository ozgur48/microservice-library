package com.turkcell.fineservice.infrastructure;


import com.turkcell.fineservice.domain.model.*;
import org.springframework.stereotype.Component;

@Component
public class FineEntityMapper {
    public JpaFineEntity toEntity(Fine fine){
        return new JpaFineEntity(
                fine.fineId().value(),
                fine.loanId().value(),
                fine.memberId().value(),
                fine.staffId().value(),
                fine.amount().value(),
                fine.fineStatus(),
                fine.fineType(),
                fine.appliedAt().value(),
                fine.reason().value()
        );
    }

    public Fine toDomain(JpaFineEntity jpaFineEntity){
        return Fine.rehydrate(
                new FineId(jpaFineEntity.id()),
                new LoanId(jpaFineEntity.loanId()),
                new MemberId(jpaFineEntity.memberId()),
                new StaffId(jpaFineEntity.staffId()),
                jpaFineEntity.status(),
                new Amount(jpaFineEntity.amount()),
                new Reason(jpaFineEntity.reason()),
                jpaFineEntity.fineType(),
                new AppliedAt(jpaFineEntity.appliedAt())
        );
    }
}
