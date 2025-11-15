package com.turkcell.memberservice.application.mapper;

import com.turkcell.memberservice.application.dto.UpdatedMemberResponse;
import com.turkcell.memberservice.domain.model.Member;
import org.springframework.stereotype.Component;

@Component
public class UpdateMemberMapper {
    public UpdatedMemberResponse toResponse(Member member){
        return new UpdatedMemberResponse(
                member.name().value(),
                member.email().value(),
                member.phone().value(),
                member.address().value()
        );
    }
}
