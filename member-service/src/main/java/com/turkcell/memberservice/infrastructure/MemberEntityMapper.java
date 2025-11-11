package com.turkcell.memberservice.infrastructure;

import com.turkcell.memberservice.domain.model.*;
import org.springframework.stereotype.Component;

@Component
public class MemberEntityMapper {
    public JpaMemberEntity toEntity(Member member){
        return new JpaMemberEntity(
                member.id().value(),
                member.name().value(),
                member.email().value(),
                member.phone().value(),
                member.address().value(),
                member.memberShipLevel(),
                member.memberStatus()
        );
    }
    public Member toDomain(JpaMemberEntity entity){
        return Member.rehydrate(
                new MemberId(entity.id()),
                new Name(entity.name()),
                new Email(entity.email()),
                new Phone(entity.phone()),
                new Address(entity.address()),
                entity.memberShipLevel(),
                entity.memberStatus()
        );
    }
}
