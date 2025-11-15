package com.turkcell.memberservice.application.mapper;

import com.turkcell.memberservice.application.dto.MemberDetails;
import com.turkcell.memberservice.application.dto.MemberShipLevelDto;
import com.turkcell.memberservice.application.dto.MemberStatusDto;
import com.turkcell.memberservice.domain.model.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberDetailsMapper {
    public MemberDetails toResponse(Member member){
        MemberShipLevelDto memberShipLevel = MemberShipLevelDto.valueOf(member.memberShipLevel().name());
        MemberStatusDto memberStatusDto = MemberStatusDto.valueOf(member.memberStatus().name());

        return new MemberDetails(
                member.name().value(),
                member.email().value(),
                member.phone().value(),
                member.address().value(),
                memberShipLevel,
                memberStatusDto
        );
    }
}
